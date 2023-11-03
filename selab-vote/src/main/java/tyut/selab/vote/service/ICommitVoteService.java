package tyut.selab.vote.service;

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.DTO.VoteResultRequest;
import tyut.selab.vote.exception.VoteException;

@Service
public interface ICommitVoteService {



     /**
      *   提交投票信息
      * @param voteInfoRequest
      * @throws VoteException
      */
     void commitVote(VoteResultRequest voteResultRequest) throws VoteException;

}
