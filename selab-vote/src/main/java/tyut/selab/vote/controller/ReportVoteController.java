package tyut.selab.vote.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.po.VoteReport;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.service.ReportVoteService;

import java.util.ArrayList;

/**
 * @className: ReportVoteController
 * @author: lizhichao
 * @description: TODO  投票举报信息
 * @date: 2023/10/3 18:25
 * @version: 1.0
 */
@RestController
@RequestMapping("/vote/report")
@Anonymous
public class ReportVoteController {

    @Autowired
    private ReportVoteService reportVoteService;

    /**
     *   提交举报信息
     * @param voteReport
     * @return
     */
    @PostMapping("/submit")
    public AjaxResult submitReportVote(@RequestBody VoteReport voteReport) throws VoteException {
        Integer flag = reportVoteService.submitReportVote(voteReport);
        if(flag==0) throw new VoteException("该投票已结束");
        if(flag==2) throw new VoteException("该投票已被冻结");
        return AjaxResult.success("举报成功");
    }


    /**
     * 查看举报信息
     * @param voteId
     * @return
     */
    @PostMapping("/view/{voteId}")
    public AjaxResult viewReportVote(@PathVariable Long voteId){
        return AjaxResult.success(reportVoteService.viewReportVote(voteId));
    }

}