package tyut.selab.vote.service;

import tyut.selab.vote.domain.vo.Questionnaire;

/**
 * 发起投票
 * @author Big_bai on 2022/11/19
 */
public interface IStartVoteService {
    //发起一个投票
    public void initAVote(Questionnaire questionnaire,Long userId);
}
