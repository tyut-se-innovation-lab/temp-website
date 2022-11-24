package tyut.selab.vote.tools;


import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;

import java.util.List;

/**
 * Vo和Po相互转换器<br>
 * VoPoConverter was added for colleagus in order to help you to convert Pos to Vos or Vos to Pos
 * @author Big_bai on 2022/11/19
 */
public interface IVoPoConverterTool {
    Questionnaire poToVo(VoteInfo voteInfo, List<PoVoteOption> voteOptions, List<VoteResult> voteResults);
}
