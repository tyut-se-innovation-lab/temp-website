package tyut.selab.vote.service;

import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

public interface IShowRoughVoteListService {
    /**
     * 展示所有投票（粗略）
     * @return 信息列表
     */
    List<Questionnaire> showAllVote();

    /**
     * 展示未过期的投票（粗略）
     * @return 信息列表
     */
    List<Questionnaire> showAllUsefulVote();
}
