package tyut.selab.vote.tools;


import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;

import java.util.List;

/**
 * Vo和Po相互转换器<br>
 * VoPoConverter was added for colleagus in order to help you to convert Pos to Vos or Vos to Pos
 * @author Big_bai on 2022/11/19
 */
public interface IVoPoConverterTool {
    Questionnaire poToVo(VoteInfo voteInfo, List<PoVoteOption> voteOptions, List<VoteResult> voteResults);
    /**
     * 问卷转换
     * @param voteInfo
     * @return
     */
    Questionnaire info(VoteInfo voteInfo);
    /**
     * 问题转换
     * @param options 问题
     * @return
     */
    List<VoteQue> que(List<PoVoteOption>options);
    /**
     * 选项转换
     * @param options 选项
     * @param voteResults 投票结果
     * @return
     */
    List<VoteOption>options(List<PoVoteOption>options,List<VoteResult>voteResults);
    /**
     * 判断选项是否被选中
     * @param option 选项
     * @param voteResults 投票结果
     * @return
     */
//    Questionnaire options(List<PoVoteOption> voteOptions, List<VoteResult> voteResults);
    int isSelect(PoVoteOption option,List<VoteResult>voteResults);
    /**
     * 文本框的显示
     * @param option 选项
     * @param voteResults 投票结果
     * @return
     */
    String getOther(PoVoteOption option,List<VoteResult>voteResults);
}
