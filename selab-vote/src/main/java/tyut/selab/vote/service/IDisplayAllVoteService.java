package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

public interface IDisplayAllVoteService {
    /**
     * 查看全部投票
     * @param userId 用户名
     */
    List<Questionnaire>displayAllVote(String userId);

    /**
     * 查看我参与的投票列表
     * @param userId
     */
    List<Questionnaire>displayMyJoinVote(String userId);
    /**
     * 查看我创建的投票列表
     * @param userId 用户名
     */
    List<Questionnaire>displayMyStartVote(String userId);

    /**
     * 提前结束投票
     * @param voteId
     * @return
     */
    int finishVote(String voteId);
}
