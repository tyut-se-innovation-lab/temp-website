package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.po.VoteReport;

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
public class ReportVoteController {

    /**
     *   提交举报信息
     * @param voteReport
     * @return
     */
    @PostMapping("/submit")
    public AjaxResult submitReportVote(@RequestBody VoteReport voteReport){

        // 检查投票是否未结束
        // 如果数量大于a, 冻结投票 （修改投票status）
        return AjaxResult.success();
    }

    /**
     *  查看举报信息
     * @param voteId
     * @return
     */
     @PostMapping("/view/{voteId}")
    public AjaxResult viewReportVote(@PathVariable Long voteId){

         return AjaxResult.success(new ArrayList<VoteReport>());
    }



}
