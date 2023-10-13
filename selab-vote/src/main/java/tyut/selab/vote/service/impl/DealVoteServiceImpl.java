/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: DealVoteServiceImpl
 * Author: 22932
 * Date: 2023/10/9 13:19:44
 * Description:
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;

import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.mapper.DealVoteMapper;
import tyut.selab.vote.service.DealVoteService;
import tyut.selab.vote.tools.TimeDealTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: DealVoteServiceImpl
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/9 13:19:44
 */
@Service
public class DealVoteServiceImpl implements DealVoteService {

    @Autowired
    private DealVoteMapper dealVoteMapper;

    @Override
    public Integer launchVote(VoteInfo voteInfo) {
//        Long userId = SecurityUtils.getUserId();
//        voteInfo.setUserId(userId);

        voteInfo.setStatus(VoteStatus.UNDERWAY);
        // TODO 是否需要判断截止时间合理性
        dealVoteMapper.saveVoteInformation(voteInfo);
        List<VoteOption> voteOptionList = new ArrayList<>();
        voteInfo.getVoteOptionVoList().forEach(voteOptionVo -> {
            VoteOption voteOption = new VoteOption();
            voteOption.setVoteId(voteOptionVo.getVoteId());
            voteOption.setOptionType(voteOptionVo.getOptionType());
            voteOption.setContent(voteOptionVo.getContent());
            voteOptionList.add(voteOption);
        });
        dealVoteMapper.saveVoteOptionInformation(voteOptionList);
        dealVoteMapper.saveVoteWeightInformation(voteInfo.getVoteWeights());
        return null;
    }

    @Override
    public Integer withdrawVote(Long voteId) {
        if(TimeDealTool.judgeVoteFinish(dealVoteMapper.queryVoteDeadTime(voteId))){
            //未到截止时间,撤回投票
            dealVoteMapper.updateVoteStatus(voteId, VoteStatus.WITHDRAW);
            return 1;
        }else{
            //此刻超出截止时间，结束投票
            dealVoteMapper.updateVoteStatus(voteId,VoteStatus.FINISH);
            return 0;
        }
    }

    @Override
    public Integer HandlingFrozenVote(Long voteId,Integer handel) {
        if(TimeDealTool.judgeVoteFinish(dealVoteMapper.queryVoteDeadTime(voteId))){
            //未到截止时间
            //handel为1则恢复正常
            if(handel == 1){
                dealVoteMapper.updateVoteStatus(voteId, VoteStatus.UNDERWAY);
                return 2;
            }else{
                dealVoteMapper.updateVoteStatus(voteId, VoteStatus.FINISH);
                return 1;
            }
        }else{
            //此刻超出截止时间，结束投票
            dealVoteMapper.updateVoteStatus(voteId,VoteStatus.FINISH);
            return 0;
        }
    }

    @Override
    public Integer deleteVote(Long voteId) {
        return dealVoteMapper.deleteVote(voteId);
    }
}
