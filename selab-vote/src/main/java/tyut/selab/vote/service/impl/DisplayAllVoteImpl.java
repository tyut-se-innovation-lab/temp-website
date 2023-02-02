package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IDisplayAllVoteService;
import tyut.selab.vote.tools.GetSysTime;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DisplayAllVoteImpl implements IDisplayAllVoteService {
    @Autowired
    FindInfoDBMapper displayAllVoteMapper;

    /**
     * 返回历史投票信息粗略列表
     * @param userId 用户名  ？？？
     * @return 返回投票列表
     */
    @Override
    public List<Questionnaire> displayAllVote(Long userId) {
        List<VoteInfo> voteInfos = displayAllVoteMapper.displayAllVote(userId);
        VoPoConverterTool tool = new VoPoConverterTool();
        List<Questionnaire>questionnaireList = new ArrayList<>();
        for (VoteInfo voteInfo : voteInfos) {
            Questionnaire info = tool.info(voteInfo);
            questionnaireList.add(info);
        }
        return questionnaireList;
    }

    /**
     * 获取可参与的（未过期）投票粗略信息
     * @param usrId
     * @return
     */
    @Override
    public List<Questionnaire> displayAllUsefulVotes(Long usrId) {
        List<VoteInfo> voteInfos = displayAllVoteMapper.displayAllUsefulVote(usrId);
        VoPoConverterTool tool = new VoPoConverterTool();
        List<Questionnaire>questionnaireList = new ArrayList<>();
        for (VoteInfo voteInfo : voteInfos) {
            Questionnaire info = tool.info(voteInfo);
            questionnaireList.add(info);
        }
        return questionnaireList;
    }

    /**
     * 我参与的投票信息列表
     * @param userId
     * @return 返回我参与的投票列表
     */
    @Override
    public List<Questionnaire> displayMyJoinVote(Long userId) {
        List<VoteResult> optionId = displayAllVoteMapper.getOptionId(userId);
        if (optionId.isEmpty()) return null;
        List<PoVoteOption> voteId = displayAllVoteMapper.getVoteId(optionId);
        if (voteId.isEmpty()) return null;
        List<VoteInfo> voteInfos = displayAllVoteMapper.displayMyHistoryVote(voteId);
        VoPoConverterTool tool = new VoPoConverterTool();
        List<Questionnaire>questionnaireList = new ArrayList<>();
        for (VoteInfo voteInfo : voteInfos) {
            Questionnaire info = tool.info(voteInfo);
            questionnaireList.add(info);
        }
        return questionnaireList;
    }

    /**
     * 返回我发起过的投票信息列表
     * @param userId 用户名
     * @return 返回我创建的投票列表
     */
    @Override
    public List<Questionnaire> displayMyStartVote(Long userId) {
        List<VoteInfo> voteInfos = displayAllVoteMapper.displayMyStartVote(userId);
        VoPoConverterTool tool = new VoPoConverterTool();
        List<Questionnaire>questionnaireList = new ArrayList<>();
        for (VoteInfo voteInfo : voteInfos) {
            Questionnaire info = tool.info(voteInfo);
            questionnaireList.add(info);
        }
        return questionnaireList;
    }

    /**
     * 提前结束投票
     *
     * @param voteId
     * @return
     */
    @Override
    public int finishVote(Long voteId) {
        String nowDate = GetSysTime.getNowDate();
        return displayAllVoteMapper.finishVote(nowDate,voteId);
    }
}

