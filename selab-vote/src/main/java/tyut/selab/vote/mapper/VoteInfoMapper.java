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
import tyut.selab.vote.domain.DTO.VoteInfoLaunchDTO;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteWeight;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.enums.VoteType;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: VoteInfoMapper
 * @Description:   投票信息mapper
 * @Author: 22932
 * @Date: 2023/10/11 18:52:28
 */
public interface VoteInfoMapper {

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

    /** TODO
     * 修改删除状态
     * @param voteId
     * @return
     */
    Integer deleteVote(@Param("voteId") Long voteId);

    /**
     * 保存投票信息
     * @param voteInfoLaunchDTO
     * @return
     */
    Integer saveVoteInformation(@Param("voteInfoLaunchDTO") VoteInfoLaunchDTO voteInfoLaunchDTO);



    /**
     *  查询投票状态 (返回到VoteInfo VoteStatus 属性)
     * @param voteId
     * @return
     */
    VoteInfo getVoteStatus(Long voteId);

    VoteType getVoteType(Long voteId);

}
