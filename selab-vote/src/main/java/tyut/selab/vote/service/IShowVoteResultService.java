package tyut.selab.vote.service;

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;

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
    VoteInfo getVoteResultDetailsByVoteId(Long voteId);

}
