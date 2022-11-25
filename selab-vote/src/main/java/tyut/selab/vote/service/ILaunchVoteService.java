package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;

import java.util.List;
import java.util.Map;

/**
 * 发起投票
 * @author Big_bai on 2022/11/19
 */
public interface ILaunchVoteService {
    //发起一个投票

    public void LaunchVote(VoteInfo voteInfo, List<PoVoteOption> questions, Map<PoVoteOption,List<PoVoteOption>> options, Long userId);

    public void LaunchVote(VoteInfo voteInfo, PoVoteOption voteOption, Long userId);

}
