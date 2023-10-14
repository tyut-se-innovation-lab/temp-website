package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Mapper;
import tyut.selab.vote.domain.po.VoteUser;

import java.util.List;


public interface VoteUserMapper {


    /**
     *  查看投票人员
     * @param voteId
     * @return
     */
   List<VoteUser> getListByVoteId(Long voteId);


    /**
     *  增加投票人员
     * @param voteUsers
     * @return
     */
   Integer insertVoteUser(List<VoteUser> voteUsers);

}
