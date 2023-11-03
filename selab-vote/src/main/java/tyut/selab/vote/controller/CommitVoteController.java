package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.domain.DTO.VoteResultRequest;
import tyut.selab.vote.exception.VoteException;
import tyut.selab.vote.service.ICommitVoteService;
import tyut.selab.vote.service.IShowVoteResultService;

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
    @Autowired
    private IShowVoteResultService showVoteResultService;
    @Autowired
    private ICommitVoteService commitVoteService;


    /**
     *  提交投票
     * @param voteResultRequest
     * @return
     */

    @PostMapping("/dovote")
    public AjaxResult doVote( @RequestBody VoteResultRequest voteResultRequest) throws VoteException {
        commitVoteService.commitVote(voteResultRequest);
        return AjaxResult.success(showVoteResultService.getVoteInfoByVoteId(voteResultRequest.getVoteId()));
    }


}
