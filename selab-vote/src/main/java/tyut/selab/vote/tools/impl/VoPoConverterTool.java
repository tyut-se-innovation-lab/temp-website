package tyut.selab.vote.tools.impl;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.enums.VoteOptionType;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.tools.IVoPoConverterTool;
import tyut.selab.vote.tools.WeightControl;
import tyut.selab.vote.tools.getSysTime;

import java.util.Date;
import java.util.List;

/**
 * @author Big_bai on 2022/11/21
 */
//public class VoPoConverterTool implements IVoPoConverterTool {
//    @Override
//    public Questionnaire poToVo(VoteInfo voteInfo, VoteOption voteOption, VoteResult voteResult) {
//        return null;
//    }
//
//    @Override
//    public VoteInfo voToVoteInfo(Questionnaire questionnaire,Long userId) {
//        VoteInfo voteInfo = new VoteInfo();
//        voteInfo.setUserId(userId);
//        voteInfo.setTitle(questionnaire.getTitle());
//        voteInfo.setContent(questionnaire.getContent());
//        voteInfo.setDeadline(questionnaire.getDeadline());
//        voteInfo.setCreateTime(getSysTime.stringToDate(getSysTime.getNowDate()));
//        voteInfo.setWeight(WeightControl.getWeight());
//        return voteInfo;
//    }
//
//    @Override
//    public VoteOption voToVoteOption(Questionnaire questionnaire) {
//
//        return null;
//    }
//
//    @Override
//    public VoteResult voToVoteResult(Questionnaire questionnaire) {
//        return null;
//    }
//
//    @Override
//    public Questionnaire poToVo(List<VoteInfo> voteInfos, List<VoteOption> voteOptions, List<VoteResult> voteResults) {
//        return null;
//    }
//}
