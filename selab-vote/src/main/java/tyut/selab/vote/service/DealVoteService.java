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
     * @param voteId
     * @return
     */
    Integer withdrawVote(Long voteId);

    /**
     * 处理冻结投票
     * @param voteId
     * @return
     */
    Integer HandlingFrozenVote(Long voteId,Integer handel);

    /**
     * 删除投票
     * @param voteId
     * @return
     */
    Integer deleteVote(Long voteId);
}
