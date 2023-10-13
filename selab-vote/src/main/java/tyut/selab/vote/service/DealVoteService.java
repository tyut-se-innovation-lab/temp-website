package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;


/**
 * @author: iyaovo
 * @version: 1.0
 */
public interface DealVoteService {
    /**
     * 发布投票
     * @param voteInfo
     * @return
     */
    Integer launchVote(VoteInfo voteInfo);

    /**
     * 撤回投票
     * @param vote_id
     * @return
     */
    Integer withdrawVote(Long vote_id);

    /**
     * 处理冻结投票
     * @param vote_id
     * @return
     */
    Integer HandlingFrozenVote(Long vote_id);
}
