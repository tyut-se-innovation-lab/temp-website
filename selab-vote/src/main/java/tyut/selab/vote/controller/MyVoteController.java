package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.IDisplayAllVoteService;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.service.IWithdrowVoteService;

import java.util.Iterator;
import java.util.List;

/**
 * @author lv
 */
@RestController
@RequestMapping("/selab/vote/mine")
public class MyVoteController {

    @Autowired
    private IDisplayAllVoteService iDisplayAllVoteService;
    @Autowired
    private IWithdrowVoteService withdrowVoteService;

    /**
     * 用户自己的历史投票信息(粗略)
     *
     * @param userId 用户id
     * @return 返回我参与的投票列表
     */
    @GetMapping("/joined/lists")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult displayMyALLVote(@RequestBody Long userId) {
        return AjaxResult.success(iDisplayAllVoteService.displayMyJoinVote(userId));
    }


    /**
     * 查看我创建的投票列表（粗略）
     *
     * @param userId 用户id
     * @return 我创建的投票列表（粗略）
     */
    @GetMapping("/launched/lists")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult myLaunchVote(@RequestBody Long userId){
        return AjaxResult.success(iDisplayAllVoteService.displayMyStartVote(userId));
    }

    /**
     * 对发起的投票进行撤回
     * @param voteId 问卷id
     * @return 撤回成功与否
     */
    @DeleteMapping("/delete")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult delectVote(@RequestBody Long voteId){
        return withdrowVoteService.delectVoteInfoById(voteId) == null?
                AjaxResult.success("撤回成功"):
                AjaxResult.error("撤回失败");
    }

    /**
     * 提前结束功能
     * @param voteId 问卷id
     * @return 操作成功与否
     */
    @PutMapping("/finish")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult finishVote(@RequestBody Long voteId){
        return iDisplayAllVoteService.finishVote(voteId) != 0?
                AjaxResult.success("操作成功"):
                AjaxResult.error("操作失败");
    }
}
