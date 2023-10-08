package tyut.selab.vote.tools.impl;

import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;

import java.util.ArrayList;
import java.util.List;

public class VoPoConverterTool {
    /**
     * 问卷转换
     * @param voteInfo
     * @return
     */
    public static Questionnaire info(VoteInfo voteInfo) {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setTitle(voteInfo.getTitle());
        questionnaire.setContent(voteInfo.getContent());
        questionnaire.setDeadline(voteInfo.getDeadline());
        questionnaire.setId(voteInfo.getId());
        questionnaire.setCreatedTime(voteInfo.getCreateTime());
        questionnaire.setStatus(voteInfo.getStatus());
        questionnaire.setPeoples(voteInfo.getPeoples());
        return questionnaire;
    }

    /**
     * 问题转换
     * @param options 问题
     * @return
     */
    public static List<VoteQue> que(List<PoVoteOption> options) {
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
     * @return
     */
    public static List<VoteOption> options(List<PoVoteOption> options) {
        ArrayList<VoteOption> voteOptions = new ArrayList<>();
        for (PoVoteOption option : options) {
            VoteOption voteOption = new VoteOption();
            voteOption.setId(option.getId());
            voteOption.setType(option.getOptionType());
            voteOption.setPercentage(option.getPercentage());
//            voteOption.setIsSelect(isSelect(option,voteResults));
//            String other = getOther(option, voteResults);
//            if(other != null){
//                voteOption.setContent(other);
//            }else
            voteOption.setContent(option.getContent());
            voteOptions.add(voteOption);
        }
        return voteOptions;
    }

    /**
     * 选项转换(无是否选中，无文本框）
     * @param options
     * @return
     */
    public static List<VoteOption> option(List<PoVoteOption> options) {
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
//    private static Boolean isSelect(PoVoteOption option, List<VoteResult> voteResults) {
//        for (VoteResult voteResult : voteResults) {
//            if(voteResult.getVoteOptionId() == option.getId()){
//                return true;
//            }
//        }
//        return false;
//    }

    /**
     * 文本框的显示
     * @param option 选项
     * @param voteResults 投票结果
     * @return
     */
//    private static String getOther(PoVoteOption option, List<VoteResult> voteResults) {
//        for (VoteResult voteResult : voteResults) {
//            if(voteResult.getVoteOptionId() == option.getId() && !voteResult.getContent().equals(option.getContent())){
//                return voteResult.getContent();
//            }
//        }
//        return null;
//    }

}
