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
    /**
     * 上传一个新问卷
     * @return 问卷的id
     */
    void launchVote(VoteInfo voteInfo, List<PoVoteOption> voteOptions);

}
