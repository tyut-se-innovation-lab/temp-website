package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

public interface IDisplayAllVote {
    /**
     * 展示所有投票
     * @return 所有查询结果的vo对象
     */
    List<Questionnaire>displayAllVote(Long userId);

}
