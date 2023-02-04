package tyut.selab.vote.service;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteResult;

import java.util.Date;
import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 14:07
 */
public interface ICommitVoteService {

    /**
     * 提交投票结果信息
     * @param results
     */
    public int commitVoteResult(List<VoteResult> results);

    /**
     * 获取投票截止时间
     * @param voteId
     * @return
     */
    public Date getDeadLineById(Long voteId);
}
