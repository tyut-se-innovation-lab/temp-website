package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;

/**
 * @className: ParticipateVoteController
 * @author: lizhichao
 * @description: TODO  参与投票
 * @date: 2023/10/8 16:25
 * @version: 1.0
 */
@RequestMapping("/vote/commit")
@RestController
public class CommitVoteController {


    /**
     *  提交投票
     * @param voteResult
     * @return
     */
    @PostMapping("/dovote")
    public AjaxResult doVote(@RequestBody VoteResult voteResult){
        // userId 后端传值
        return AjaxResult.success(new VoteInfo());
    }


}
