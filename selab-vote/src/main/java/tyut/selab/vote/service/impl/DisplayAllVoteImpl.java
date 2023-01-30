package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IDisplayAllVoteService;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisplayAllVoteImpl implements IDisplayAllVoteService {
    @Autowired
    FindInfoDBMapper displayAllVoteMapper;

    /**
     * 查看所有投票列表
     * @param userId 用户名
     * @return 返回投票列表
     */
    @Override
    public List<Questionnaire> displayAllVote(String userId) {
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
     * c查看我参与的投票列表
     * @param userId
     * @return 返回我参与的投票列表
     */
    @Override
    public List<Questionnaire> displayMyJoinVote(String userId) {
        List<VoteResult> optionId = displayAllVoteMapper.getOptionId(userId);
        List<PoVoteOption> voteId = displayAllVoteMapper.getVoteId(optionId);
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
     * 查看我创建的投票列表
     * @param userId 用户名
     * @return 返回我创建的投票列表
     */
    @Override
    public List<Questionnaire> displayMyStartVote(String userId) {
        List<VoteInfo> voteInfos = displayAllVoteMapper.displayMyStartVote(userId);
        VoPoConverterTool tool = new VoPoConverterTool();
        List<Questionnaire>questionnaireList = new ArrayList<>();
        for (VoteInfo voteInfo : voteInfos) {
            Questionnaire info = tool.info(voteInfo);
            questionnaireList.add(info);
        }
        return questionnaireList;
    }
}

