package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

public interface IDisplayAllVoteService {
    /**
     * 查看所有投票列表
     * @param userId 用户名
     */
    List<Questionnaire>displayAllVote(Long userId);

    /**
     * 未过期的投票列表
     * @param usrId
     * @return
     */
    List<Questionnaire>displayAllUsefulVotes(Long usrId);
    /**
     * c查看我参与的投票列表
     * @param userId
     */
    List<Questionnaire>displayMyJoinVote(Long userId);
    /**
     * 查看我创建的投票列表
     * @param userId 用户名
     */
    List<Questionnaire>displayMyStartVote(Long userId);
    /**
     * 提前结束投票
     * @param voteId
     * @return
     */
    int finishVote(String voteId);
}


