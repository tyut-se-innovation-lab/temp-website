package tyut.selab.vote.service;

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.VoteResultDetails;
import tyut.selab.vote.exception.VoteException;

import java.util.List;
@Service
public interface IShowVoteResultService {


    /**
     *  获取投票信息
     * @param voteId
     * @return
     */
    VoteInfo getVoteInfoByVoteId(Long voteId);

    /**
     *  获取投票详细信息
     * @param voteId
     * @return
     */
    VoteResultDetails getVoteResultDetailsByVoteId(Long voteId);

}
