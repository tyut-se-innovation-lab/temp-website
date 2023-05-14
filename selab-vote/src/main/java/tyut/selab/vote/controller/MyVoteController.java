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

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

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
    @Autowired
    private IDisplayVoteResultService iDisplayVoteResultService;

    /**
     * 暂不实现
     *
     * 用户参与的历史投票信息(粗略)
     *
     * @return 返回我参与的投票列表
     */
    @GetMapping("/joined/lists")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult displayMyALLVote() {
        return AjaxResult.success(iDisplayAllVoteService.displayMyJoinVote(getUserId().toString()));
    }

    /**
     * 暂不实现
     *
     * 查看我创建的投票列表（粗略）
     *
     * @return 我创建的投票列表（粗略）
     */
    @GetMapping("/launched/lists")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult myLaunchVote(){
        return AjaxResult.success(iDisplayAllVoteService.displayMyStartVote(getUserId().toString()));
    }


    /**
     * 暂不实现
     *
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
     * 暂不实现
     *
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
