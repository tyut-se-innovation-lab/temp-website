package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.po.VoteInfo;


/**
 * @className: DealVoteController
 * @author: lizhichao
 * @description: TODO 处理投票内容  （发布 撤回等）
 * @date: 2023/10/2 20:41
 * @version: 1.0
 */
@RequestMapping("/vote/deal")
@RestController
public class DealVoteController {

    // 发布投票者拥有当前投票的所有权 权限是活的
    /**
     *   发布投票
     * @param voteInfoVo 投票详情Vo类对象
     * @return
     */
    @PostMapping("/launch")
    public AjaxResult launchVote(@RequestBody VoteInfo voteInfoVo){


        Long userId = SecurityUtils.getUserId();
        voteInfoVo.setUserId(userId);

        return AjaxResult.success("投票上传成功");

    }

    /**
     * 发起者撤回投票
     * @param voteId
     * @return
     */
    @GetMapping("/withdraw/{voteId}")
    public AjaxResult withdrawVote(@PathVariable Long voteId){
        // 检查投票是否未结束
       return AjaxResult.success();
    }

    /**
     *   撤回被举报的投票经管理员审核后撤回
     * @param   voteId:   ,handel: 0/1  0为关闭 1 为 恢复正常
     * @return
     */
    @GetMapping("/handle/{voteId}")
    public AjaxResult HandlingFrozenVote(@PathVariable Long voteId,Integer handel){
        //检查投票是否未结束
        return AjaxResult.success();
    }

    /**
     * 查询特定投票
     * @param voteId
     * @return
     */
    @GetMapping("/delete/{voteId}")
    public AjaxResult deleteVote(@PathVariable Long voteId){
        return AjaxResult.success(new VoteInfo());
    }

}
