package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteRange;
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
    Integer insertVoteResult(List<VoteResult> voteResults);





    /**
     *  通过 voteId 和 optionId 或 userId （可变参数） 查询投票结果
     * @param voteId
     * @param optionId
     * @return
     */
    List<VoteResult> getVoteResultByOptionIdAndVoteId(Long voteId,Long optionId,Long userId);


    /**
     *  查询票数
     * @param voteId
     * @param optionId
     * @return
     */
    Integer getVoteReultCount(Long voteId,Long optionId);

    /**
     *  查询实名投票的用户加密id
     * @param optionId
     * @param voteId
     * @return
     */
    List<String> getParseUserId(Long optionId,Long voteId);
    /**
     *  通过userId查询用户名字
     * @param userIds
     * @return
     */
    List<String> getNickName(List<Long> userIds);


}
