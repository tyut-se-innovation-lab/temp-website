package tyut.selab.vote.mapper;


import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Weight;

import java.util.Date;
import java.util.List;

/**
 * 查找信息
 * @author Big_bai on 2022/11/22
 */
public interface FindInfoDBMapper {

    /**
     * 根据id查询权重
     * @param id
     * @return
     */
    Weight getWeightById(Long id);

    /**
     * 获取上一次使用的投票id
     * @return
     */
    Long getLastUseWeightId();

    /**
     * 根据id查询投票信息
     * @param id
     * @return
     */
    public List<VoteInfo> findVoteInfoById(long id);

    public int updateVoteInfoById(String status,long id);
    /**
     * 查看所有列表
     * @param userId
     */
    List<VoteInfo> displayAllVote(@Param("userId")Long userId);

    /**
     * 根据用户ID查找所选选项ID
     * @param userId
     */
    List<VoteResult>getOptionId(@Param("userId")Long userId);

    /**
     * 根据选项ID查找投票ID
     * @param optionId
     */
    List<PoVoteOption>getVoteId(@Param("optionIds")List<VoteResult>optionId);

    /**
     * 查看我参与的投票
     * @param voteId
     */
    List<VoteInfo>displayMyHistoryVote(@Param("voteId")List<PoVoteOption>voteId);

    /**
     * 获取可参与的（未过期）投票粗略信息
     * @param userId
     * @return
     */
    List<VoteInfo>displayAllUsefulVote(@Param("userId")Long userId);

    /**
     * 查看我发起的投票
     * @param userId
     */
    List<VoteInfo>displayMyStartVote(@Param("userId")Long userId);

    /**
     * 根据选项ID查找投票者ID
     * @param optionId
     * @return
     */
    List<VoteResult>displayVoteUser(@Param("optionId")List<PoVoteOption> optionId);
    List<VoteResult>displayVoteUsers(@Param("optionId")Long optionId);

    /**
     * 根据投票ID查找投票问题
     * @param voteTd
     * @return
     */
    List<PoVoteOption>getVoteOptions(@Param("voteId")Long voteTd);

    /**
     * 根据投票ID查找投票选项
     * @param voteTd
     * @return
     */
    List<PoVoteOption>getVoteOptionByVoteId(@Param("voteId")Long voteTd);

    /**
     * 根据投票ID查找投票
     * @param voteTd
     * @return
     */
    VoteInfo getVoteByVoteId(@Param("voteId")Long voteTd);

    /**
     * 根据问题找选项
     * @param parentId
     * @return
     */
    List<PoVoteOption>getVoteOptionByParentId(@Param("parentId")Long parentId);

    /**
     * 根据使用者id和选项找结果
     * @param userId
     * @param optionsId
     * @return
     */
    List<VoteResult>getResByUserIdAndOptionId(@Param("userId")Long userId, @Param("optionIds")List<PoVoteOption>optionsId);

    /**
     * 查看参与该投票的人数
     * @param voteId
     * @return
     */
    int theNumOfJoinVote(@Param("voteId")Long voteId);

    /**
     * 提前结束 vote
     * @param nowDate
     * @param id
     * @return
     */
    int finishVote(@Param("nowDate")String nowDate,@Param("id")String id);

    int getRoleById(long id);
}
