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

    void writeVoteInfoToDB(VoteInfo voteInfo);
    void writeVoteOptionToDB(VoteOption voteOption);
    void writeVoteResultToDB(VoteResult voteResult);
}
