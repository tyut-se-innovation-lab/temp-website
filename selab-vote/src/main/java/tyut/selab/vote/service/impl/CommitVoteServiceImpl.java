package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.mapper.VoteResultMapper;
import tyut.selab.vote.mapper.VoteUserMapper;
import tyut.selab.vote.service.ICommitVoteService;
import tyut.selab.vote.tools.AESUtil;

import java.util.Date;

/**
 * @className: CommitVoteIMpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/14 20:59
 * @version: 1.0
 */
public class CommitVoteServiceImpl implements ICommitVoteService {

    @Autowired
    VoteInfoMapper voteInfoMapper;
    @Autowired
    VoteResultMapper voteResultMapper;
    @Autowired
    VoteUserMapper voteUserMapper;
    @Override
    public void commitVote(VoteResult  voteResult) throws VoteException {
        Long userId = SecurityUtils.getUserId();
        Long voteId = voteResult.getVoteId();
        VoteInfo voteStatus = voteInfoMapper.getVoteStatus(voteId);
        if(voteStatus.getStatus().getId() == 1){
            Date date = voteInfoMapper.queryVoteDeadTime(voteId);
            if(date.after(new Date())){
                voteInfoMapper.updateVoteStatus(voteId, VoteStatus.FINISH);
                throw new VoteException("投票已经结束了");
            }
           // 无论是否匿名。数据库存放的都是加密后的userId--- 如果实名投票的话回在查询的时候进行解密
            String encryptUserId = AESUtil.encrypt(voteResult.getUserId());
            voteResult.setUserId(encryptUserId);
            Integer rows = voteResultMapper.insertVoteResult(voteResult);
            Integer row1 = voteUserMapper.updateIsComplete(voteId, userId);

        }else{
            throw new VoteException("投票异常");
        }
    }

}
