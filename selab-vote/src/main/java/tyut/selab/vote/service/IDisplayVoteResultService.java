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
    Questionnaire displayNumOfResult(Long voteId,String userId);
}
