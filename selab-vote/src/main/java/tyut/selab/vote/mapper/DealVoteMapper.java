/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: DealVoteMapper
 * Author: 22932
 * Date: 2023/10/11 18:52:28
 * Description: \
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteWeight;
import tyut.selab.vote.enums.VoteStatus;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: DealVoteMapper
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/11 18:52:28
 */
public interface DealVoteMapper {
    /**
     * 查询投票截止时间
     * @param voteId
     * @return
     */
    Date queryVoteDeadTime(@Param("voteId") Long voteId);

    /**
     * 修改投票状态
     * @param voteId
     * @return
     */
    Integer updateVoteStatus(@Param("voteId") Long voteId,@Param("status") VoteStatus status);

    /**
     * 修改删除状态
     * @param voteId
     * @return
     */
    Integer deleteVote(@Param("voteId") Long voteId);

    /**
     * 保存投票信息
     * @param voteInfo
     * @return
     */
    Integer saveVoteInformation(@Param("voteInfo") VoteInfo voteInfo);

    /**
     * 保存选项信息
     * @param voteOptionList
     * @return
     */
    Integer saveVoteOptionInformation(@Param("voteOptionList") List<VoteOption> voteOptionList);

    /**
     * 保存权重信息
     * @param voteWeightList
     * @return
     */
    Integer saveVoteWeightInformation(@Param("voteWeightList") List<VoteWeight> voteWeightList);

}

