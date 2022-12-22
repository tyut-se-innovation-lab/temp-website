package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.PoVoteOption;

import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.service.ILaunchVoteService;
import tyut.selab.vote.service.IWeightControlService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上传课表相关
 * @author Big_bai on 2022/11/19
 */
@Service
public class LaunchVoteService implements ILaunchVoteService {

    @Autowired
    private InsertInfoDBMapper insertInfoDBMapper;
    @Autowired
    IWeightControlService weightControlService;

    /**
     * 上传课表<br>
     * 需要加一个线程
     * @param voteInfo 问卷
     * @param voteOptions 选项和问题
     */
    @Override
    public void launchVote(VoteInfo voteInfo, List<PoVoteOption> voteOptions) {
        List<PoVoteOption> questions = new ArrayList<>();
        Map<PoVoteOption, List<PoVoteOption>> questionsToOptions = new HashMap<>();
        voteInfo.setWeight(weightControlService.getNowVoteWeight().getId());
        //将问卷存入，获取问卷的唯一id
        voteInfo.setStatus("1");
        insertInfoDBMapper.writeVoteInfoToDB(voteInfo);
        //将问题和答案拼装
        for(int i=0;i<voteOptions.size();i++){
            if(voteOptions.get(i).getOptionType().equals("Q")){
                voteOptions.get(i).setVoteId(voteInfo.getId());
                voteOptions.get(i).setParentId(-1L);
                questions.add(voteOptions.get(i));
                List<PoVoteOption> voteOptionsForEachQue = new ArrayList<>();
                for(int j=i+1;;j++){
                    if(j==voteOptions.size()||voteOptions.get(j).getOptionType().equals("Q")){
                        questionsToOptions.put(voteOptions.get(i),voteOptionsForEachQue);
                        i=j-1;
                        break;
                    }
                    voteOptionsForEachQue.add(voteOptions.get(j));
                }
            }
        }
        insertInfoDBMapper.writeVoteOptionToDB(questions);
        //问卷的id和问题的id赋予选项,然后存入
        for (PoVoteOption p:questions) {
            for(PoVoteOption v:questionsToOptions.get(p)){
                v.setVoteId(voteInfo.getId());
                v.setParentId(p.getId());
            }
            insertInfoDBMapper.writeVoteOptionToDB(questionsToOptions.get(p));
        }
    }
}
