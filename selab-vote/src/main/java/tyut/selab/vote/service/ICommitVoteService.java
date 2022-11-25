package tyut.selab.vote.service;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteResult;

import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 14:07
 */
public interface ICommitVoteService {

    /**
     * 上传用户投票结果信息
     * @param results
     */
    public int commitVoteResult(List<VoteResult> results);
}
