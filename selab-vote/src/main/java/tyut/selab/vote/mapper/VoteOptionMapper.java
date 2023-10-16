package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteOption;

import java.util.List;

/**
 * @className: VoteOptionMapper
 * @author: lizhichao
 * @description: TODO 投票选项查询
 * @date: 2023/10/13 21:09
 * @version: 1.0
 */
public interface VoteOptionMapper {

    /**
     *  通过voteId查询选项
     * @param voteId
     * @return
     */
    List<VoteOption> getVoteOptionByVoteId(Long voteId);

    /**
     *   保存选项信息
     * @param voteOptions
     * @return
     */
    Integer saveVoteOptionInformation(@Param("voteOptions") List<VoteOption> voteOptions);




}