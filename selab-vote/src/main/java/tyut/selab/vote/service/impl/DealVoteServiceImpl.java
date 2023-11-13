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
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.DTO.VoteInfoLaunchDTO;
import tyut.selab.vote.domain.po.VoteUser;
import tyut.selab.vote.domain.po.VoteWeight;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.exception.VoteDeletedException;
import tyut.selab.vote.exception.VoteFreezedException;
import tyut.selab.vote.exception.VoteOverTimeException;
import tyut.selab.vote.exception.VoteProcessedException;
import tyut.selab.vote.mapper.*;
import tyut.selab.vote.service.DealVoteService;
import tyut.selab.vote.tools.TimeDealTool;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @ClassName: DealVoteServiceImpl
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/9 13:19:44
 */
@Service
public class DealVoteServiceImpl implements DealVoteService {

    @Autowired
    private VoteInfoMapper voteInfoMapper;

    @Autowired
    private VoteWeightMapper voteWeightMapper;

    @Autowired
    private VoteOptionMapper voteOptionMapper;

    @Autowired
    private ReportVoteMapper reportVoteMapper;

    @Autowired
    private VoteUserMapper voteUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public void launchVote(VoteInfoLaunchDTO voteInfoLaunchDTO) {
        List<VoteUser> voteUserList = new ArrayList<>();
        for (VoteWeight voteWeight:voteInfoLaunchDTO.getVoteWeights()
             ) {
            for (SysUser sysUser :sysRoleMapper.selectUsersByRoleId(voteWeight.getRoleId())
                    ) {
                if(Objects.equals(sysUser.getRoles().get(0).getRoleId(), voteWeight.getRoleId())){
                    voteUserList.add(new VoteUser(voteInfoLaunchDTO.getVoteId(),sysUser.getUserId(),null,0));
                }
            }
        }
        voteUserMapper.insertVoteUser(voteUserList);
    //初始化投票属性
//        Long userId = SecurityUtils.getUserId();
        //voteInfo.setUserId(userId);
        voteInfoLaunchDTO.setStatus(VoteStatus.UNDERWAY);
        voteInfoLaunchDTO.setDelFlag("1");
        voteInfoMapper.saveVoteInformation(voteInfoLaunchDTO);
        voteOptionMapper.saveVoteOptionInformation(voteInfoLaunchDTO.getVoteOptionVoList());
        voteWeightMapper.saveVoteWeightInformation(voteInfoLaunchDTO.getVoteWeights());
    }

    @Override
    public void withdrawVote(Long voteId) throws VoteOverTimeException, VoteFreezedException {
        if(!TimeDealTool.judgeVoteFinish(voteInfoMapper.queryVoteDeadTime(voteId)))
            throw new VoteOverTimeException("该投票已结束");

        if(voteInfoMapper.getVoteStatus(voteId).getStatus() == VoteStatus.FREEZE)
            throw new VoteFreezedException("该投票已被冻结");

        voteInfoMapper.updateVoteStatus(voteId, VoteStatus.WITHDRAW);
    }

    @Override
    public Integer HandlingFrozenVote(Long voteId,Integer handel) throws VoteOverTimeException, VoteProcessedException {
        VoteStatus voteStatus = voteInfoMapper.getVoteStatus(voteId).getStatus();

        if(!TimeDealTool.judgeVoteFinish(voteInfoMapper.queryVoteDeadTime(voteId)))
            throw new VoteOverTimeException("该投票已结束");

        if(voteStatus == VoteStatus.CLOSED || voteStatus == VoteStatus.UNDERWAY)
            throw new VoteProcessedException("该投票已被处理");

        if(handel == 1){
            if(voteStatus == VoteStatus.FREEZE){
                //解除冻结后举报数要清0
                reportVoteMapper.clearReportCount(voteId);
            }
            voteInfoMapper.updateVoteStatus(voteId, VoteStatus.UNDERWAY);
            return 1;
        }else{
            voteInfoMapper.updateVoteStatus(voteId, VoteStatus.CLOSED);
            return 0;
        }
    }

    @Override
    public Integer deleteVote(Long voteId) throws VoteDeletedException {
        if(voteInfoMapper.getVoteDelFlag(voteId) == 0)
            throw new VoteDeletedException("该投票已被删除");

        return voteInfoMapper.deleteVote(voteId);
    }
}
