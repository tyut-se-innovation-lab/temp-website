package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;

/**
 * 新增投票
 * @author Big_bai on 2022/11/19
 */
public interface IStartVoteMapper {
    /**
     * 写入问卷信息
     * @param voteInfo 问卷信息
     * @return 是否成功传入
     */
    public int wrightVoteInfo(VoteInfo voteInfo);
}
