package tyut.selab.vote.tools.impl;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.tools.IVoPoConverterTool;

/**
 * @author Big_bai on 2022/11/19
 */
public class VoPoConverterTool implements IVoPoConverterTool{

    @Override
    public Questionnaire poToVo(VoteInfo voteInfo, VoteOption voteOption, VoteResult voteResult) {
        return null;
    }

    @Override
    public VoteInfo voToVoteInfo(Questionnaire questionnaire) {
        return null;
    }

    @Override
    public VoteOption voToVoteOption(Questionnaire questionnaire) {
        return null;
    }

    @Override
    public VoteResult voToVoteResult(Questionnaire questionnaire) {
        return null;
    }
}
