package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.vo.Questionnaire;

/**
 * 发起投票
 * @author Big_bai on 2022/11/19
 */
public interface ILaunchVoteService {
    //发起一个投票
    public void LaunchVote(VoteInfo voteInfo, VoteOption voteOption, Long userId);
}
