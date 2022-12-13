package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.impl.CommitVoteServiceImpl;
import tyut.selab.vote.service.impl.DisplayAllVoteImpl;
import tyut.selab.vote.service.impl.DisplayVoteResultServiceImpl;
import tyut.selab.vote.service.impl.WithdrowVoteServiceImpl;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import java.util.List;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * @Author: Gulu
 * @Date: 2022/11/24 20:42
 */
@RestController
@RequestMapping("/selab/vote")
public class CommitController {
    @Autowired
    CommitVoteServiceImpl commitVoteService;

    @Autowired
    DisplayAllVoteImpl displayAllVote;

    @Autowired
    DisplayVoteResultServiceImpl displayVoteResultService;

    @Autowired
    WithdrowVoteServiceImpl withdrowVoteService;

    /**
     * 参与投票展示当前用户所有投票粗略信息
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vote:join')")
    @PostMapping("/commit/list")
    @ResponseBody
    public AjaxResult listRoughInformation(){
        return AjaxResult.success(displayAllVote.displayAllVote(getUserId().toString()));
    }

    /**
     * 查询返回投票的详细结果
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vote:join')")
    @PostMapping("/commit/allInfo")
    public AjaxResult listDetails(@RequestBody Questionnaire questionnaire){
        return AjaxResult.success(displayVoteResultService.displayVoteResult(questionnaire.getId(),getUserId().toString()));
    }

    /**
     * 提交用户投票结果
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vote:join')")
    @PostMapping("/commit")
    public AjaxResult commitVoteResult(@RequestBody Questionnaire questionnaire){
        List<VoteResult> voteResults = new VoPoConverterTool().toVoteResult(questionnaire, getUserId().toString());
        commitVoteService.commitVoteResult(voteResults);
        return AjaxResult.success("上传成功");
    }

}