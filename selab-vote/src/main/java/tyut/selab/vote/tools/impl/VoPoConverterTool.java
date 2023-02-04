package tyut.selab.vote.tools.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.tools.AnonymousControl;
import tyut.selab.vote.tools.GetSysTime;
import tyut.selab.vote.tools.IVoPoConverterTool;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @author Big_bai on 2022/11/21
 */

@Component
public class VoPoConverterTool implements IVoPoConverterTool {

    /**
     * voteResult类型转换
     * @param questionnaire 问卷
     * @return 投票结果集合
     */
    @Override
    public List<VoteResult> toVoteResult(Questionnaire questionnaire,Long userId){
        List<VoteResult> voteResults = new ArrayList<>();
        VoteResult result = new VoteResult();
        List<VoteQue> voteQues = questionnaire.getVoteQues();
        for (VoteQue voteQue : voteQues){
            List<VoteOption> options = voteQue.getOptions();
            for (VoteOption voteOption : options){
                if (voteOption.getIsSelect() == 1){  //isSelect值为1时，证明该选项已选或者文本框已填写内容
                    result.setVoteOptionId(voteOption.getId());
//                    result.setUserId(AnonymousControl.encrypt(userId));
                    result.setContent(voteOption.getContent());
                    result.setCreateTime(GetSysTime.getNow());
                }
                voteResults.add(result);
            }
        }
        return voteResults;
    }

    @Override
    public Questionnaire poToVo(VoteInfo voteInfo, List<PoVoteOption> voteOptions, List<VoteResult> voteResults) {
        return null;
    }

    /**
     * 问卷转换
     * @param voteInfo
     * @return
     */
    @Override
    public Questionnaire info(VoteInfo voteInfo) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setTitle(voteInfo.getTitle());
        questionnaire.setContent(voteInfo.getContent());
        questionnaire.setDeadline(voteInfo.getDeadline());
        questionnaire.setId(voteInfo.getId());
        questionnaire.setCreatTime(voteInfo.getCreateTime());
        questionnaire.setStatus(voteInfo.getStatus());
        questionnaire.setPeoples(voteInfo.getPeoples());
        return questionnaire;
    }

    /**
     * 问题转换
     * @param options 问题
     * @return
     */
    @Override
    public List<VoteQue> que(List<PoVoteOption> options) {
        ArrayList<VoteQue> voteQues = new ArrayList<>();
        for (PoVoteOption option : options) {
            VoteQue voteQue = new VoteQue();
            voteQue.setId(option.getId());
            voteQue.setType(option.getOptionType());
            voteQue.setQueContent(option.getContent());
            voteQues.add(voteQue);
        }
        return voteQues;
    }

    /**
     * 选项转换
     * @param options 选项
     * @param voteResults 投票结果
     * @return
     */
    @Override
    public List<VoteOption> options(List<PoVoteOption> options,List<VoteResult>voteResults) {
        ArrayList<VoteOption> voteOptions = new ArrayList<>();
        for (PoVoteOption option : options) {
            VoteOption voteOption = new VoteOption();
            voteOption.setId(option.getId());
            voteOption.setContent(option.getContent());
            voteOption.setType(option.getContent());
            voteOption.setPercentage(option.getPercentage());
            voteOption.setIsSelect(isSelect(option,voteResults));
            voteOption.setOther(getOther(option,voteResults));
            voteOptions.add(voteOption);
        }
        return voteOptions;
    }

    /**
     * 选项转换(无是否选中，无文本框）
     * @param options
     * @return
     */
    @Override
    public List<VoteOption> option(List<PoVoteOption> options) {
        ArrayList<VoteOption> voteOptions = new ArrayList<>();
        for (PoVoteOption option : options) {
            VoteOption voteOption = new VoteOption();
            voteOption.setId(option.getId());
            voteOption.setContent(option.getContent());
            voteOption.setType(option.getContent());
            voteOption.setPercentage(option.getPercentage());
            voteOptions.add(voteOption);
        }
        return voteOptions;
    }

    /**
     * 判断选项是否被选中
     * @param option 选项
     * @param voteResults 投票结果
     * @return
     */
    @Override
    public int isSelect(PoVoteOption option, List<VoteResult> voteResults) {
        for (VoteResult voteResult : voteResults) {
            if(voteResult.getVoteOptionId()== option.getId()){
                return 1;
            }
        }
        return 0;
    }

    /**
     * 文本框的显示
     * @param option 选项
     * @param voteResults 投票结果
     * @return
     */
    @Override
    public String getOther(PoVoteOption option, List<VoteResult> voteResults) {
        for (VoteResult voteResult : voteResults) {
            if(voteResult.getVoteOptionId() == option.getId() && !voteResult.getContent().equals(option.getContent())){
                return voteResult.getContent();
            }
        }
        return null;
    }

    @Override
    public Boolean updateTime(VoteInfo voteInfo) {
        Date date = new Date();
        if(voteInfo.getDeadline().before(date)){
            return true;
        }else return false;
    }


    /**
     * vo转为voteInfo
     * @param questionnaire 一个问卷
     * @return VoteInfo
     */
    public static VoteInfo voToVoteInfo(Questionnaire questionnaire,Long userId){
        VoteInfo voteInfo = new VoteInfo();
        voteInfo.setUserId(userId);
        voteInfo.setContent(questionnaire.getContent());
        voteInfo.setCreateTime(GetSysTime.getNow());
        voteInfo.setDeadline(questionnaire.getDeadline());
        voteInfo.setTitle(questionnaire.getTitle());
        return voteInfo;
    }

    public static List<PoVoteOption> voToPoVoteOptions(Questionnaire questionnaire){
        List<PoVoteOption> voteOptions = new ArrayList<>();
        List<VoteQue> ques = questionnaire.getVoteQues();
        for(int i = 0;i<ques.size();i++){
            PoVoteOption que = new PoVoteOption();
            que.setOptionType(ques.get(i).getType());
            que.setContent(ques.get(i).getQueContent());
            voteOptions.add(que);
            for(VoteOption v:ques.get(i).getOptions()){
                PoVoteOption p = new PoVoteOption();
                p.setContent(v.getContent());
                p.setOptionType(v.getType());
                voteOptions.add(p);
            }

        }
        return voteOptions;
    }

}