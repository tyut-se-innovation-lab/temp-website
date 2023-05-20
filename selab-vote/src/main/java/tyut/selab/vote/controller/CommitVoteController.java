package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.ICommitVoteService;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * 上传投票
 * @author Big-Bai
 **/
@RestController
@RequestMapping("/selab/vote/join")
public class CommitVoteController {
    @Autowired
    private ICommitVoteService commitVoteService;
    /**
     * 获取可以参与的投票的粗略信息
     * @return
     */
    @PreAuthorize("@ss.hasAnyPermi('vote:join')")
    @GetMapping("/list")
    public AjaxResult getVoteListPermitted(){
        return AjaxResult.success(commitVoteService.listAllowed(getUserId()));
    }
    /**
     * 参与投票
     * @return
     */
    @PreAuthorize("@ss.hasAnyPermi('vote:join')")
    @PostMapping("/")
    public AjaxResult commitVote(@RequestBody Questionnaire questionnaire){
        commitVoteService.recordSelection(questionnaire,getUserId());
        return AjaxResult.success("上传成功");
    }
}
