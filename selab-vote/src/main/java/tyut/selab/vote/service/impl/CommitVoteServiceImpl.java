package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.DTO.VoteResultRequest;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.mapper.VoteResultMapper;
import tyut.selab.vote.mapper.VoteUserMapper;
import tyut.selab.vote.mapper.VoteWeightMapper;
import tyut.selab.vote.service.ICommitVoteService;
import tyut.selab.vote.tools.AESUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: CommitVoteIMpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/14 20:59
 * @version: 1.0
 */
@Service
public class CommitVoteServiceImpl implements ICommitVoteService {

    @Autowired
    VoteInfoMapper voteInfoMapper;
    @Autowired
    VoteResultMapper voteResultMapper;
    @Autowired
    VoteUserMapper voteUserMapper;
    @Autowired
    VoteWeightMapper voteWeightMapper;
    @Override
    public void commitVote(VoteResultRequest voteResultRequest) throws VoteException {
        Long voteId = voteResultRequest.getVoteId();
        VoteInfo voteStatus = voteInfoMapper.getVoteStatus(voteId);
        if (voteStatus.getStatus().getId() == 1) {
            Date date = voteInfoMapper.queryVoteDeadTime(voteId);
            if (date.before(DateUtils.getNowDate())) {
                voteInfoMapper.updateVoteStatus(voteId, VoteStatus.FINISH);
                throw new VoteException("投票已经结束了");
            }
//            Long userId = SecurityUtils.getUserId();
            Long userId = 1L;
            Integer weight = voteWeightMapper.getWeightByUserId(voteResultRequest.getVoteId(),userId);
            voteResultRequest.setCreateTime(DateUtils.getNowDate());
            // 无论是否匿名。数据库存放的都是加密后的userId--- 如果实名投票的话回在查询的时候进行解密
            String encryptUserId = AESUtil.encrypt(userId.toString());
            List<VoteResult> voteResults = voteResultConverter(voteResultRequest, weight);
            Integer rows = voteResultMapper.insertVoteResult(voteResults);
            Integer row1 = voteUserMapper.updateIsComplete(voteId, userId);

        } else if (voteStatus.getStatus().getId() == 2){
            throw new VoteException("投票已经结束了");
        }else{
            throw new VoteException("投票异常");
        }

    }
        private List<VoteResult> voteResultConverter(VoteResultRequest voteResultRequest,Integer weight){
            List<VoteResult> voteResults = new ArrayList<>();
            List<Long> voteOptionIds = voteResultRequest.getVoteOptionIds();
            Long userId = SecurityUtils.getUserId();

            for (Long voteOptionId : voteOptionIds) {
                VoteResult voteResult = new VoteResult();
                voteResult.setVoteOptionId(voteOptionId);
                voteResult.setCreateTime(voteResultRequest.getCreateTime());
                voteResult.setVoteId(voteResultRequest.getVoteId());
                voteResult.setContent(voteResultRequest.getContent());
                voteResult.setIsAnonymous(voteResultRequest.getIsAnonymous());
                voteResult.setUserId(voteResultRequest.getUserId());
                voteResult.setWeight(weight);
                voteResults.add(voteResult);
            }
            return voteResults;
        }


}
