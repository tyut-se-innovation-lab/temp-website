package tyut.selab.vote.mapper;

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
     * @param voteResult
     * @return
     */
    Integer insertVoteResult(VoteResult voteResult);





    /**
     *  通过 voteId 和 optionId 和 userId （可变参数） 查询投票结果
     * @param voteId
     * @param optionId
     * @return
     */
    List<VoteResult> getVoteResultByOptionIdAndVoteId(Long voteId,Long optionId,Long userId);


    /**
     *  查询票数  optionId为可加参数
     * @param voteId
     * @param optionId
     * @return
     */
    Integer getVoteReultCount(Long voteId,Long optionId);

}
