package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteRange;
import tyut.selab.vote.mapper.QueryVoteMapper;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.service.QueryVoteService;

import java.util.List;
import java.util.Map;

/**
 * @className: QueryVoteServiceImpl
 * @author: zhy
 * @description: TODO 查询投票实体类
 * @date: 2023/10/10 21:00
 * @version: 1.0
 */
public class QueryVoteServiceImpl implements QueryVoteService {
    @Autowired
    private VoteInfoMapper voteInfoMapper;
    @Autowired
    private QueryVoteMapper queryVoteMapper;

    @Override
    public List<VoteRange> queryForMyReport() {
        Long userId = SecurityUtils.getUserId();
        List<VoteRange> myReport = queryVoteMapper.getMyReport(userId);
        return myReport;
    }

    @Override
    public List<VoteRange> queryForMyHold() {
        Long userId = SecurityUtils.getUserId();
        List<VoteRange> byLaunchUserId = queryVoteMapper.getMyHold(userId);
        return byLaunchUserId;
    }

    @Override
    public List<VoteRange> getAllVote() {
        List<VoteRange> allVote = queryVoteMapper.getAllVote();
        return allVote;
    }

    @Override
    public List<VoteRange> getForUntreated() {
        List<VoteRange> forUntreated = queryVoteMapper.getForUntreated();
        return forUntreated;
    }


    @Override
    public List<VoteRange> queryForParams(Map<String,Integer> params) {
        Long userId = SecurityUtils.getUserId();
        Integer isEnd = params.get("isEnd");
        Integer isParticipate = params.get("isParticipate");
        List<VoteRange> voteRangeByParams = queryVoteMapper.queryForParams(isEnd, isParticipate, userId);
        return voteRangeByParams;
    }


    @Override
    public VoteInfo getVoteInfoById(Long voteId) {
        VoteInfo voteInfoByVoteId = queryVoteMapper.getVoteInfoByVoteId(voteId);
        return voteInfoByVoteId;
    }


}
