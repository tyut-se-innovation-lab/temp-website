package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteResult;

import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 14:07
 */
public interface ICommitVoteService {

    /**
     * 参与投票
     * @param results
     */
    public void commitVote(List<VoteResult> results);
}
