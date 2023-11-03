package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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


    /**
     *  查询是否已参与投票
     * @param voteId
     * @param userId
     * @return
     */
    String getIsCompleteByUserIdAndVoteId(@Param("voteId") Long voteId,@Param("userId") Long userId);

    /**
     *  修改is_complete字段为已完成
     * @param voteId
     * @param userId
     * @return
     */
    Integer updateIsComplete(@Param("voteId") Long voteId,@Param("userId") Long userId);

    /**
     *  根据特定属性查询list
     * @param voteUser
     * @return
     */

    List<VoteUser> getVoteUserList(VoteUser voteUser);

}
