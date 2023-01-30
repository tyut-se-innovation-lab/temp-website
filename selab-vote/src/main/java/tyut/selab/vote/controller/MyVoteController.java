package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.IDisplayAllVoteService;
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
     * 显示用户自己的历史投票内容
     *
     * @param userId
     * @return 返回我参与的投票列表
     */
    @GetMapping("/joined/list")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult displayMyALLVote(@RequestBody Long userId) {
        // 获取用户自己的历史投票信息
        List<Questionnaire> questionnaires = iDisplayAllVoteService.displayMyJoinVote(userId.toString());
        // 转换 status  数字->String
        Iterator<Questionnaire> iterator = questionnaires.iterator();
        while(iterator.hasNext()){
            Questionnaire next = iterator.next();
            if ("0".equals(next.getStatus())){
                next.setStatus("进行中");
            }else if ("1".equals(next.getStatus())){
                next.setStatus("结束");
            }else if ("2".equals(next.getStatus())){
                next.setStatus("被撤回");
            }else next.setStatus("转换失败，请手动改信息~");
        }
        return AjaxResult.success(questionnaires);
    }

    /**
     * 查看我创建的投票列表
     * @param userId 用户名
     * @return 返回我创建的投票列表
     */
    @GetMapping("/launched/list")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult myLaunchVote(@RequestBody Long userId){
        return AjaxResult.success(iDisplayAllVoteService.displayMyStartVote(userId.toString()));
    }

    /**
     * 根据voteid对发起的投票进行撤回
     * @param voteId
     * @return
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
     */
    @PutMapping("/finish")
    @PreAuthorize("@ss.hasPermi('vote:mine')")
    public AjaxResult finishVote(@RequestBody Long voteId){
        return iDisplayAllVoteService.finishVote(String.valueOf(voteId)) != 0?
                AjaxResult.success("操作成功"):
                AjaxResult.error("操作失败");
    }
}
