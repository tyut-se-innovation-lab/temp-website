package tyut.selab.vote.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.DTO.VoteInfoLaunchDTO;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.exception.VoteDeletedException;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.exception.VoteProcessedException;
import tyut.selab.vote.service.DealVoteService;

import java.util.HashMap;
import java.util.Map;

/**
 * @className: DealVoteController
 * @author: lizhichao
 * @description:  处理投票内容  （发布 撤回 ）
 * @date: 2023/10/2 20:41
 * @version: 1.0
 */
@RequestMapping("/vote/deal")
@RestController
@Anonymous
public class DealVoteController {

    @Autowired
    private DealVoteService dealVoteService;

    // 发布投票者拥有当前投票的所有权 权限是活的
    /**
     *   发布投票
     * @param voteInfoLaunchDTO 投票详情Vo类对象
     * @return
     */
    @PostMapping("/launch")
    public AjaxResult launchVote(@RequestBody VoteInfoLaunchDTO voteInfoLaunchDTO){
        dealVoteService.launchVote(voteInfoLaunchDTO);
        return AjaxResult.success("投票上传成功");
    }


    /**
     * 发起者撤回投票
     * @param voteId
     * @return
     */
    @GetMapping("/withdraw/{voteId}")
    public AjaxResult withdrawVote(@PathVariable Long voteId){
        dealVoteService.withdrawVote(voteId);
        return AjaxResult.success("撤回成功");
    }


    /**
     *   撤回/被举报的投票经管理员审核后撤回
     * @param   voteId:   ,handel: 0/1  0为关闭 1 为 恢复正常
     * @return
     */
    @GetMapping("/handle/{voteId}/{handel}")
    public AjaxResult HandlingFrozenVote(@PathVariable Long voteId,@PathVariable Integer handel) throws VoteException, VoteProcessedException {
        if(dealVoteService.HandlingFrozenVote(voteId, handel) == 1){
            return AjaxResult.success("该投票重新开启");
        }else{
            return AjaxResult.success("该投票被关闭");
        }

    }

    /**
     * 删除投票
     * @param voteId
     * @return
     */
    @GetMapping("/delete/{voteId}")
    public AjaxResult deleteVote(@PathVariable Long voteId) throws VoteDeletedException {
        dealVoteService.deleteVote(voteId);
        return AjaxResult.success("删除成功");
    }

}
