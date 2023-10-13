package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteUser;

public interface VoteUserMapper {


    /**
     *  查看投票人员
     * @param voteId
     * @return
     */
   VoteUser getListByVoteId(Long voteId);

}
