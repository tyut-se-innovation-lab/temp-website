package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.service.IWithdrowVoteService;

@RestController
@RequestMapping("/selab/vote/delete")
public class DelectVoteController {

    @Autowired
    private IWithdrowVoteService withdrowVoteService;

    /**
     * 根据voteid对投票进行撤回
     * @param voteId
     * @return
     */
    @DeleteMapping("")
    @PreAuthorize("@ss.hasPermi('vote:join')")
    public AjaxResult delectVote(@RequestBody Long voteId){
        return withdrowVoteService.delectVoteInfoById(voteId) == null?
                AjaxResult.success("撤回成功"):
                AjaxResult.error("撤回失败");
    }
}
