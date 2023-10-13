package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.DateUtils;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.mapper.VoteResultMapper;

/**
 * @className: ParticipateVoteServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 16:20
 * @version: 1.0
 */
public class CommitServiceImpl implements ICommitVoteService {

    private VoteResultMapper voteResultMapper;
    @Override
    public VoteInfo commitVote(VoteResult voteResult) throws VoteException {
        // 先从数据库查询时间
//        if((voteResult.getVoteOptionId()== null && voteResult.getContent() == null) || voteResult.getUserId() == null){
//            throw new RuntimeException();
//        }else{
//            voteResult.setCreateTime(DateUtils.getNowDate());
//        }
        voteResult.setCreateTime(DateUtils.getNowDate());
        Integer rows = voteResultMapper.insertVoteResult(voteResult);
        if(rows != 1){
         throw new VoteException("投票提交未知异常");
        }
        // 查询实时信息  先判断是否实时（sql语句判断）

            return null;
    }


}
