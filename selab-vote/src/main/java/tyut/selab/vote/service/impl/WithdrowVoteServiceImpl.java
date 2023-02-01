package tyut.selab.vote.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IWithdrowVoteService;

import java.util.List;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * 对已发布的投票进行撤回
 */
@Service
@Slf4j
public class WithdrowVoteServiceImpl implements IWithdrowVoteService {
    @Autowired
    private FindInfoDBMapper findInfoDBMapper;

    @Override
    public List<VoteInfo> delectVoteInfoById(long id){
        //根据id找出数据库的投票信息
        List<VoteInfo> voteInfoById = findInfoDBMapper.findVoteInfoById(id);
        //非空判断
        if (voteInfoById.isEmpty())   return null;
        //改变状态
        voteInfoById.get(0).setStatus(String.valueOf(2));
        findInfoDBMapper.updateVoteInfoById(voteInfoById.get(0).getStatus(),voteInfoById.get(0).getId());
        return voteInfoById;
    }
}
