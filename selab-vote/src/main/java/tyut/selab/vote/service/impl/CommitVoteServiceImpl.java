package tyut.selab.vote.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.service.ICommitVoteService;

import java.util.Date;
import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/11/23 18:46
 */
@Service
public class CommitVoteServiceImpl implements ICommitVoteService {
    @Autowired
    InsertInfoDBMapper insertInfoDBMapper;

    @Autowired
    FindInfoDBMapper findInfoDBMapper;

    /**
     * 提交用户投票结果
     * @param results 用户投票结果集合
     */
    @Override
    public int commitVoteResult(List<VoteResult> results) {
        return insertInfoDBMapper.writeVoteResultToDB(results);
    }

    @Override
    public Date getDeadLineById(Long voteId) {
        return findInfoDBMapper.getDeadLineById(voteId);
    }


}
