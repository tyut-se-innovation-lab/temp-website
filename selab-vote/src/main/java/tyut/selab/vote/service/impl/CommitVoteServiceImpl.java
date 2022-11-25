package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.service.ICommitVoteService;

import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/11/23 18:46
 */
@Service
public class CommitVoteServiceImpl implements ICommitVoteService {
    @Autowired
    InsertInfoDBMapper insertInfoDBMapper;

    /**
     * 上传用户投票结果
     * @param results 用户投票结果集合
     */
    @Override
    public int commitVoteResult(List<VoteResult> results) {
        return insertInfoDBMapper.writeVoteResultToDB(results);
    }
}
