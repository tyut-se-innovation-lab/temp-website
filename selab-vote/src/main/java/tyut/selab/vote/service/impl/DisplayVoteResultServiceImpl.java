package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import java.util.ArrayList;
import java.util.List;
@Service
public class DisplayVoteResultServiceImpl implements IDisplayVoteResultService {
    @Autowired
    FindInfoDBMapper findInfoDBMapper;

    /**
     * 返回某次投票详细信息
     * 返回我发起过的投票详细信息
     * 我参与的投票详细信息
     * @param voteId 投票ID
     * @param userId 申请者id
     * @return
     */
    @Override
    public Questionnaire displayVoteResult(Long voteId,String userId) {
        VoPoConverterTool tool = new VoPoConverterTool();
        VoteInfo voteByVoteId = findInfoDBMapper.getVoteByVoteId(voteId);
        Questionnaire info = tool.info(voteByVoteId);
        List<PoVoteOption> voteOptions = findInfoDBMapper.getVoteOptions(voteByVoteId.getId());
        List<VoteQue> que = tool.que(voteOptions);
        for (VoteQue voteQue : que) {
            List<PoVoteOption> voteOptionByParentId = findInfoDBMapper.getVoteOptionByParentId(voteQue.getId());
            List<VoteResult> resByUserIdAndOptionId = findInfoDBMapper.getResByUserIdAndOptionId(userId, voteOptionByParentId);
            List<VoteOption> options = tool.options(voteOptionByParentId,resByUserIdAndOptionId);
            voteOptionByParentId.forEach(System.out::println);
            voteQue.setOptions(options);
        }
        info.setVoteQues(que);
        return info;
    }

    /**
     * 查看详细（百分比）
     * @param voteId 投票ID
     * @param userId 申请者ID
     * @return
     */
    @Override
    public Questionnaire displayNumOfResult(Long voteId, String userId) {
        return null;
    }
}
