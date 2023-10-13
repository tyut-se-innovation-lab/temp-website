package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteReport;

import java.util.List;

/**
 * @author: iyaovo
 * @version: 1.0
 */
public interface ReportVoteService {
    /**
     * 提交举报信息
     * @param voteReport 举报信息类
     * @return 判断投票是否结束，未结束则提交成功
     */
    Integer submitReportVote(VoteReport voteReport);

    /**
     * 查看举报信息
     * @param voteId
     * @return
     */
    List<VoteReport> viewReportVote(Long voteId);
}
