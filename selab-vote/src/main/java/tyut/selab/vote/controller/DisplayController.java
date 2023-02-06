package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.service.IDisplayVoteResultService;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

@RestController
@RequestMapping("/selab/vote/display")
public class DisplayController {

    @Autowired
    private IDisplayVoteResultService displayVoteResultService;
    /**
     * 返回某次投票详细信息
     *
     * @param voteId
     * @return
     */
    @GetMapping("")
    @PreAuthorize("@ss.hasPermi('vote:history')")
    public AjaxResult displayVote(@RequestBody Long voteId) {
        return AjaxResult.success(displayVoteResultService.displayVoteResult(voteId,getUserId().toString()));
    }
    /**
     * 查看历史投票的详细信息(未测试)
     *
     * @param voteId 投票ID
     * @return
     */
    @GetMapping("/history")
    @PreAuthorize("@ss.hasPermi('vote:history')")
    public AjaxResult displayHisVote(@RequestBody Long voteId) {
        return AjaxResult.success(displayVoteResultService.displayVoteHistory(voteId,getUserId().toString()));
    }
}
