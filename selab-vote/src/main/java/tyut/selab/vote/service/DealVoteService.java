package tyut.selab.vote.service;

import tyut.selab.vote.domain.DTO.VoteInfoLaunchDTO;
import tyut.selab.vote.exception.VoteDeletedException;
import tyut.selab.vote.exception.VoteProcessedException;


/**
 * @author: iyaovo
 * @version: 1.0
 */
public interface DealVoteService {
    /**
     * 发布投票
     *
     * @param voteInfoLaunchDTO
     */
    void launchVote(VoteInfoLaunchDTO voteInfoLaunchDTO);

    /**
     * 撤回投票
     *
     * @param voteId
     */
    void withdrawVote(Long voteId);

    /**
     * 处理冻结投票
     * @param voteId
     * @return
     */
    Integer HandlingFrozenVote(Long voteId,Integer handel) throws VoteProcessedException;

    /**
     * 删除投票
     * @param voteId
     * @return
     */
    Integer deleteVote(Long voteId) throws VoteDeletedException;
}
