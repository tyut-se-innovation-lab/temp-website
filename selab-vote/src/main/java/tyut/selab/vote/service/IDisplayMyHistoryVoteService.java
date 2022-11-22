package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

public interface IDisplayMyHistoryVoteService {
    /**
     * 展示我的投票
     * @param userId 申请者id
     * @return 投票列表
     */
    List<Questionnaire>displayMyHistory(Long userId);
}
