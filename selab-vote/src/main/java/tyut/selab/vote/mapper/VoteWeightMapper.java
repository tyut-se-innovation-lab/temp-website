package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteWeight;

import java.util.List;

public interface VoteWeightMapper {
    /**
     * 保存权重信息
     * @param voteWeightList
     * @return
     */
    Integer saveVoteWeightInformation(@Param("voteWeightList") List<VoteWeight> voteWeightList);
}
