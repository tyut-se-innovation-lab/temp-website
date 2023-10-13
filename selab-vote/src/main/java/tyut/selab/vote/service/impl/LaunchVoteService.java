package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;

import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.service.ILaunchVoteService;
import tyut.selab.vote.service.IWeightControlService;
import tyut.selab.vote.tools.GetSysTime;
import tyut.selab.vote.tools.impl.RSATool;

import java.util.*;

/**
 * 上传问卷相关
 * @author Big_bai on 2022/11/19
 */
@Service
public class LaunchVoteService implements ILaunchVoteService,Runnable {
    @Autowired
    private InsertInfoDBMapper insertInfoDBMapper;
    @Autowired
    IWeightControlService weightControlService;

    private Questionnaire questionnaire;
    private Long userId;
    private Long voteInfoId;

    @Override
    public void launchVote(Questionnaire questionnaire,long userId) {
        this.questionnaire = questionnaire;
        this.userId = userId;
        //不知道为什么加上线程就不能跑
//        Thread t = new Thread(this);
//        t.start();
        run();
    }

    @Override
    public void run() {
        VoteInfo voteInfo = getVoteInfoFromQuestionnaire();
        this.voteInfoId = memoryVoteInfoAndGetVoteInfoId(voteInfo);
        Map<PoVoteOption,List<PoVoteOption>> qAMap = mapQuesToOptions();
        Set<PoVoteOption> ques = qAMap.keySet();


        for(PoVoteOption que:ques){
            long queId = memoryQuesAndGetId(que);
            if(!"T".equals(que.getOptionType())) {
                memoryOption(queId, qAMap.get(que));
            }
        }
    }

    private VoteInfo getVoteInfoFromQuestionnaire(){
        VoteInfo voteInfo = new VoteInfo();
        voteInfo.setUserId(RSATool.encrypt(this.userId.toString()));
        voteInfo.setTitle(questionnaire.getTitle());
        voteInfo.setContent(questionnaire.getContent());
        voteInfo.setStatus("0");
        voteInfo.setDeadline(questionnaire.getDeadline());
        voteInfo.setCreateTime(GetSysTime.getNow());
        voteInfo.setWeight(weightControlService.getLastUseWeightId());
        return voteInfo;
    }
    private Map<PoVoteOption,List<PoVoteOption>> mapQuesToOptions(){
        Map<PoVoteOption,List<PoVoteOption>> queToOpt = new HashMap<>();
        List<PoVoteOption> poVoteOptions = new ArrayList<>();
        for (VoteQue vq: questionnaire.getVoteQues()) {
            PoVoteOption que = voteQueToPoVoteOption(vq);
            if(!que.getOptionType().equals("T")){
                poVoteOptions = voteOptionToPoVoteOption(vq.getOptions());
            }else {
                poVoteOptions = new ArrayList<>();
            }
            queToOpt.put(que,poVoteOptions);
        }
        return queToOpt;
    }
    private Long memoryVoteInfoAndGetVoteInfoId(VoteInfo voteInfo){
        insertInfoDBMapper.writeVoteInfoToDB(voteInfo);
        return voteInfo.getId();
    }
    private Long memoryQuesAndGetId(PoVoteOption que){
        insertInfoDBMapper.writeVoteOptionToDB(List.of(que));
        return que.getId();
    }
    private void memoryOption(long queId,List<PoVoteOption> options){
        options.forEach(option -> {option.setParentId(queId);});
        insertInfoDBMapper.writeVoteOptionToDB(options);
    }

    private PoVoteOption voteQueToPoVoteOption(VoteQue voteQues){
            PoVoteOption p = new PoVoteOption();
            p.setVoteId(voteInfoId);
            p.setParentId(-1L);
            p.setOptionType(voteQues.getType());
            p.setContent(voteQues.getQueContent());
        return p;
    }
    private List<PoVoteOption> voteOptionToPoVoteOption(List<VoteOption> options){
        List<PoVoteOption> poVoteOptions = new ArrayList<>();
        for (VoteOption vo:options) {
            PoVoteOption poVoteOption = new PoVoteOption();
            poVoteOption.setVoteId(voteInfoId);
            poVoteOption.setOptionType(vo.getType());
            poVoteOption.setContent(vo.getContent());
            poVoteOption.setPercentage(vo.getPercentage());
            poVoteOptions.add(poVoteOption);
        }
        return poVoteOptions;
    }
}
