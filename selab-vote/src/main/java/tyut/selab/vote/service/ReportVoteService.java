package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteReport;

import tyut.selab.vote.exception.VoteWithdrawnException;

import java.util.List;

/**
 * @author: iyaovo
 * @version: 1.0
 */
public interface ReportVoteService {
    /**
     * 提交举报信息
     *
     * @param voteReport 举报信息类
     */
    void submitReportVote(VoteReport voteReport) throws VoteWithdrawnException;

    /**
     * 查看举报信息
     * @param voteId
     * @return
     */
    List<VoteReport> viewReportVote(Long voteId);
}
