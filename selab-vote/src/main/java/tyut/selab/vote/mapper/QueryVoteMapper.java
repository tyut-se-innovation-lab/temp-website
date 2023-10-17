package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteRange;
import tyut.selab.vote.domain.po.VoteResultDetails;


import java.util.List;
import java.util.Map;

/**
 * @className: QueryVoteInfoMapper
 * @author: lizhichao
 * @description: TODO 查询投票信息
 * @date: 2023/10/13 21:33
 * @version: 1.0
 */
public interface QueryVoteMapper {

    /**
     * 通过voteId 查询指定投票信息
     *
     * @param voteId
     * @return
     */
    public VoteInfo getVoteInfoByVoteId(Long voteId);

    public List<VoteRange> getByLaunchUserId(Long voteId);


//    /**
//     * 查询选中指定选项的userId(加密后)
//     *
//     * @param parseUserId 加密后的userId
//     * @return
//     */
//    List<VoteRange> getParseUserIdByOptionId(String parseUserId);


    /**
     *  当 isEnd = 1 时会有查到status = 1，此时需在service层修改状态
     * @param isEnd 0 1
     * @param isParticipate 0 1
     * @return
     */
    List<VoteRange> getVoteRangeByParams(Integer isEnd, Integer isParticipate,Long userId);
}
