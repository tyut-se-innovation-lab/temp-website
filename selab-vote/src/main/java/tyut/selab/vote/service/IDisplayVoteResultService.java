package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

public interface IDisplayVoteResultService {
    /**
     * 展示历史投票结果
     * @param userId 申请者id
     * @return 查询结果
     */
    Questionnaire displayVoteResult(Long voteId,String userId);

    /**
     * 显示参与该投票的总人数
     * @param voteId 投票ID
     * @param userId 使用者ID
     * @return
     */
    int displayNumOfResult(Long voteId,String userId);

    /**
     * 显示正在进行的投票的详细信息
     * @param voteId 投票ID
     * @param userId 使用者ID
     * @return
     */
    Questionnaire displayVoteGoing(Long voteId,String userId);

    /**
     *显示历史投票的详细信息
     * @param voteId 投票ID
     * @param userId 使用者ID
     * @return
     */
    Questionnaire displayVoteHistory(Long voteId,String userId);

    /**
     * 根据voteId判断用户是否参与投票
     * @param userId
     * @param voteId
     * @return
     */
    Boolean isJoin(String userId,Long voteId);
}
