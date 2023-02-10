package tyut.selab.vote.mapper;



import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.WeightPo;
import tyut.selab.vote.domain.vo.Weight;

import java.util.List;

/**
 * 添加信息
 * @author Big_bai on 2022/11/22
 */
public interface InsertInfoDBMapper {


    void writeVoteInfoToDB(VoteInfo voteInfo);
    void writeVoteOptionToDB(List<PoVoteOption> voteOptions);



    /**
     * 添加用户投票结果
     * @param results 投票结果信息集合
     * @return 添加数据条数
     */
    int writeVoteResultToDB(@Param("results") List<VoteResult> results);
}

