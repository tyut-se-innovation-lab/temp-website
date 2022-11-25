package tyut.selab.vote.tools.impl;


import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.enums.VoteOptionType;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.tools.IVoPoConverterTool;
import tyut.selab.vote.tools.getSysTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Big_bai on 2022/11/21
 */


public class VoPoConverterTool implements IVoPoConverterTool {

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
            voteOption.setIsSelect(isSelect(option,voteResults));
            voteOption.setOther(getOther(option,voteResults));
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



}