package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteOpinionContent;
import tyut.selab.vote.domain.po.VoteResult;

import java.util.List;

/**
 * @className: ParticipateVoteMapper
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 16:42
 * @version: 1.0
 */
public interface VoteResultMapper {


    /**
     *  增加结果数据
     * @param voteResults
     * @return
     */
    Integer insertVoteResult(@Param("voteResults") List<VoteResult> voteResults);





    /**
     *  通过 voteId 和 optionId 或 userId （可变参数） 查询投票结果
     * @param voteId
     * @param optionId
     * @return
     */
    List<VoteResult> getVoteResultByOptionIdAndVoteId(@Param("voteId") Long voteId,@Param("optionId") Long optionId,@Param("userId") String userId);


    /**
     *  查询票数 (带权重)
     * @param voteId
     * @param optionId
     * @return
     */
    Integer getVoteReaultCount(@Param("voteId") Long voteId,@Param("optionId") Long optionId);

    /**
     *  查询实名投票的用户加密id
     * @param optionId
     * @param voteId
     * @return
     */
    List<String> getParseUserId(@Param("optionId") Long optionId,@Param("voteId") Long voteId);
    /**
     *  通过userId查询用户名字
     * @param userIds
     * @return
     */
    List<String> getNickName(@Param("userIds") List<Long> userIds);

    /**
     *   获取匿名投票意见
      * @param voteId null
     * @return
     */
   List<VoteOpinionContent> getOptionContent(@Param("voteId") Long voteId);
}
