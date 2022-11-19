package tyut.selab.vote.tools;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;

/**
 * Vo和Po相互转换器
 * @author Big_bai on 2022/11/19
 */
public interface IVoPoConverterTool {
    /**
     * 将Po转换为Vo
     * @param voteInfo  VoteInfo对象
     * @param voteOption po.VoteOption 对象
     * @param voteResult VoteResult对象
     * @return questionnaire
     */
    public Questionnaire poToVo(VoteInfo voteInfo,VoteOption voteOption,VoteResult voteResult);

    /**
     * 将vo转换为VoteInfo
     * @param questionnaire  一个Vo对象
     * @return voteInfo
     */
    public VoteInfo voToVoteInfo(Questionnaire questionnaire);
    /**
     * 将vo转换为po.VoteOption
     * @param questionnaire  一个Vo对象
     * @return po.voteOption
     */
    public VoteOption voToVoteOption(Questionnaire questionnaire);
    /**
     * 将vo转换为VoteResult
     * @param questionnaire  一个Vo对象
     * @return voteResult
     */
    public VoteResult voToVoteResult(Questionnaire questionnaire);
}
