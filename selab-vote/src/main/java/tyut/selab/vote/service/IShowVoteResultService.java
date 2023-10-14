package tyut.selab.vote.service;

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.exception.VoteException;

import java.util.List;
@Service
public interface IShowVoteResultService {


    /**
     *
     * @param voteId
     * @return
     * @throws VoteException
     */
    VoteInfo afterGetVoteInfoByVoteId(Long voteId) throws VoteException;

}
