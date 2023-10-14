package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.VoteUser;
import tyut.selab.vote.domain.vo.VoteOptionVo;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.mapper.QueryVoteMapper;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.mapper.VoteResultMapper;
import tyut.selab.vote.service.IShowVoteResultService;

import java.util.Date;
import java.util.List;

/**
 * @className: ShowVoteResultServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 18:14
 * @version: 1.0
 */
public class ShowVoteResultServiceImpl implements IShowVoteResultService {


    @Autowired
    QueryVoteMapper queryVoteMapper;
    @Autowired
    VoteInfoMapper voteInfoMapper;
    @Autowired
    VoteResultMapper voteResultMapper;






    @Override
    public VoteInfo afterGetVoteInfoByVoteId(Long voteId) throws VoteException {
        VoteInfo voteStatus = voteInfoMapper.getVoteStatus(voteId);
        if (voteStatus.getStatus().getId() == 1) {
            Date date = voteInfoMapper.queryVoteDeadTime(voteId);
            if (date.after(new Date())) {
                voteInfoMapper.updateVoteStatus(voteId, VoteStatus.FINISH);
                VoteInfo voteInfo1 = queryVoteMapper.getVoteInfoByVoteId(voteId);
                VoteInfo voteInfo = insertIsSelect(voteInfo1);

            }
            else{

            }

        } else if (voteStatus.getStatus().getId() == 2) {

        }


        return null;
    }
     // 实时数据
    private VoteInfo VoteInfoForRealTime(){

    }

    // 判断是否被选中
    public Boolean isChoose(List<VoteResult> voteResults,Long optionId){

        for (VoteResult voteResult:voteResults){
            if(voteResult.getVoteOptionId() == optionId){
                return true;
            }
        }
        return false;
    }
    private  VoteInfo insertIsSelect(VoteInfo voteInfo){
        Long userId = SecurityUtils.getUserId();
        List<VoteOptionVo> voteOptionVoList = voteInfo.getVoteOptionVoList();
        List<VoteResult> voteResultByOptionIdAndVoteId = voteResultMapper.getVoteResultByOptionIdAndVoteId(voteInfo.getVoteId(),null, userId);
        for (VoteOptionVo voteOptionVo:voteOptionVoList){
            voteOptionVo.setSelect(false);
            //判断是否被选中
            for(VoteResult voteResult:voteResultByOptionIdAndVoteId){
                if(voteResult.getVoteOptionId() == voteOptionVo.getId()){
                    voteOptionVo.setSelect(true);
                }
            }
        }
        return voteInfo;
    }


}
