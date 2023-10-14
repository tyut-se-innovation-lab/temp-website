package tyut.selab.vote.mapper;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteRange;



import java.util.List;

/**
 * @className: QueryVoteInfoMapper
 * @author: lizhichao
 * @description: TODO 查询投票信息
 * @date: 2023/10/13 21:33
 * @version: 1.0
 */
public interface QueryVoteMapper {

    /**
     *  通过voteId 查询指定投票信息
     * @param voteId
     * @return
     */
    public VoteInfo getVoteInfoByVoteId(Long voteId);



    public  List<VoteRange> getByLaunchUserId(Long voteId);





}
