package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.service.ICommitVoteService;

import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 14:07
 */
@Service
public class CommitVoteServiceImpl implements ICommitVoteService {
    //Mapper对象
    @Autowired
    private ICommitVoteMapper commitVoteMapper;

    @Override
    public void commitVote(List<VoteResult> results) {
        System.out.println(commitVoteMapper.commitVote(results));
    }
}
