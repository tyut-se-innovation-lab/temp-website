package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Weight;

/**
 * 添加信息
 * @author Big_bai on 2022/11/22
 */
public interface InsertInfoDBMapper {
    Long writeWightInfoToDB(Weight w);

    int writeVoteInfoToDB(VoteInfo voteInfo);
    int writeVoteOptionToDB(VoteOption voteOption);
    int writeVoteResultToDB(VoteResult voteResult);
}
