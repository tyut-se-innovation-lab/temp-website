package tyut.selab.vote.service;

import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

public interface IShowDetailedVoteListService {
    /**
     * 展示投票详细信息
     * @param voteId 投票id
     * @return 信息列表
     */
    Questionnaire showDetailedVote(Long voteId);

}
