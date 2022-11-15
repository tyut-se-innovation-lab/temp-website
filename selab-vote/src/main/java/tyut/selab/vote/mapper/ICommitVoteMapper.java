package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteResult;

import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 12:21
 */
public interface ICommitVoteMapper {

    /**
     * 参与投票
     * @param results 投票结果信息集合
     * @return
     */
    int commitVote(List<VoteResult> results);
}
