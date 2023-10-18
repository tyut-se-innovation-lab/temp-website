package tyut.selab.vote.service;

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.DTO.VoteResultRequest;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.exception.VoteException;

@Service
public interface ICommitVoteService {



     /**
      *   提交投票信息
      * @param voteResultRequest
      * @throws VoteException
      */
     void commitVote(VoteResultRequest voteResultRequest) throws VoteException;

}
