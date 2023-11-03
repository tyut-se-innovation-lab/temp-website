package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteRange;


import java.util.List;

/**
 * @className: QueryVoteInfoMapper
 * @author: lizhichao
 * @description: TODO 查询投票信息
 * @date: 2023/10/13 21:33
 * @version: 1.0
 */
public interface QueryVoteMapper {

    /**
     * 通过voteId 查询指定投票信息
     *
     * @param voteId
     * @return
     */
    public VoteInfo getVoteInfoByVoteId(@Param("voteId") Long voteId);


    /**
     *  当 isEnd = 1 时会有查到status = 1，此时需在service层修改状态
     * @param isEnd 0 1
     * @param isParticipate 0 1
     * @return
     */
    List<VoteRange> queryForParams(Integer isEnd, Integer isParticipate,Long userId);

    /**
     *  查询我举报的投票
     */

    List<VoteRange> getMyReport(@Param("userId")Long userId);
    /**
     * 查询我发起的投票
     * @return
     */
    List<VoteRange> getMyHold(@Param("userId")Long userId);

    /**查询所有投票
     *
     * @return
     */
    List<VoteRange> getAllVote();

    /**
     * 查询冻结投票
     * @return
     */
    List<VoteRange> getForUntreated();
}
