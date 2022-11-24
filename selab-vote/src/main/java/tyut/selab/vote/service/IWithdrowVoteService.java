package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;

import java.util.List;

public interface IWithdrowVoteService {
    /**
     * 根据voteid对投票进行撤回
     * @param id  voteId
     * @return
     */
    public List<VoteInfo> findVoteInfoById(long id) throws Exception;
}
