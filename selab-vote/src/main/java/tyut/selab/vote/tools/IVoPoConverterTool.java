package tyut.selab.vote.tools;

import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.enums.VoteStatus;

import java.util.List;
import java.util.Map;

/**
 * Vo和Po相互转换器<br>
 * VoPoConverter was added for colleagus in order to help you to convert Pos to Vos or Vos to Pos
 * @author Big_bai on 2022/11/19
 */
public interface IVoPoConverterTool {
    /**
     * 将Po转换为Vo
     * @param voteInfos  VoteInfo对象
     * @param voteOptions po.VoteOption 对象
     * @param voteResults VoteResult对象
     * @return questionnaire
     */
    Questionnaire poToVo(List<VoteInfo> voteInfos, List<VoteOption> voteOptions, List<VoteResult> voteResults);


}
