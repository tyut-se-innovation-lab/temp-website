package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteRange;
import tyut.selab.vote.domain.vo.VoteResultDetails;
import tyut.selab.vote.mapper.QueryVoteMapper;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.service.QueryVoteService;

import java.util.List;

/**
 * @className: QueryVoteServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/13 22:06
 * @version: 1.0
 */
public class QueryVoteServiceImpl implements QueryVoteService {

    @Autowired
    VoteInfoMapper voteInfoMapper;
    @Autowired
    QueryVoteMapper queryVoteMapper;
    @Override
    public List<VoteRange> getForMyReport() {
        return null;
    }

    @Override
    public List<VoteRange> getForMyHold() {
        Long userId = SecurityUtils.getUserId();
        List<VoteRange> byLaunchUserId = queryVoteMapper.getByLaunchUserId(userId);
        return byLaunchUserId;
    }

    @Override
    public List<VoteRange> getForUntreated() {

        return null;
    }

    @Override
    public List<VoteRange> getForParams() {
        return null;
    }

    @Override
    public List<VoteInfo> getVoteInfoById(Long voteId) {
        return null;
    }

    @Override
    public List<VoteResultDetails> getVoteResultDetailsById(Long voteId) {
        return null;
    }
}
