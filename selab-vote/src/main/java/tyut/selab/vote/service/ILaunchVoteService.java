package tyut.selab.vote.service;

import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;
import java.util.Map;

/**
 * 发起投票
 * @author Big_bai on 2022/11/19
 */
public interface ILaunchVoteService {
    /**
     * 上传一个新问卷
     */
    void launchVote(Questionnaire questionnaire,long userId);

}
