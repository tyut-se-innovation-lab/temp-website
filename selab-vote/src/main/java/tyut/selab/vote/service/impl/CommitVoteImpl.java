package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.mapper.VoteResultMapper;
import tyut.selab.vote.service.ICommitVoteService;

import java.util.Date;

/**
 * @className: CommitVoteIMpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/14 20:59
 * @version: 1.0
 */
public class CommitVoteImpl implements ICommitVoteService {

    @Autowired
    VoteInfoMapper voteInfoMapper;
    @Autowired
    VoteResultMapper voteResultMapper;
    @Override
    public void commitVote(VoteResult  voteResult) throws VoteException {
        Long voteId = voteResult.getVoteId();
        VoteInfo voteStatus = voteInfoMapper.getVoteStatus(voteId);
        if(voteStatus.getStatus().getId() == 1){
            Date date = voteInfoMapper.queryVoteDeadTime(voteId);
            if(date.after(new Date())){
                voteInfoMapper.updateVoteStatus(voteId, VoteStatus.FINISH);
                throw new VoteException("投票已经结束了");
            }
            Integer rows = voteResultMapper.insertVoteResult(voteResult);
        }else{
            throw new VoteException("投票异常");
        }
    }
}
