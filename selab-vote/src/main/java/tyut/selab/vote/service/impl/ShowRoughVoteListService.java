package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.mapper.GetInfoDBMapper;
import tyut.selab.vote.service.IShowRoughVoteListService;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShowRoughVoteListService implements IShowRoughVoteListService {
    @Autowired
    GetInfoDBMapper mapper;
    @Autowired
    ShowDetailedVoteListService service;
    @Override
    public List<Questionnaire> showAllVote(){
        List<VoteInfo> voteInfos = mapper.displayAllVote();
        return getQuestionnaires(voteInfos);
    }

    @Override
    public List<Questionnaire> showAllUsefulVote() {
        List<VoteInfo> voteInfos = mapper.displayAllUsefulVote();
        return getQuestionnaires(voteInfos);
    }

    private List<Questionnaire> getQuestionnaires(List<VoteInfo> voteInfos) {
        List<Questionnaire>questionnaireList = new ArrayList<>();
        for (VoteInfo voteInfo : voteInfos) {
            Questionnaire questionnaire = VoPoConverterTool.info(voteInfo);
            questionnaire.setJoin(service.isJoin(voteInfo.getId()));
            questionnaireList.add(questionnaire);
        }
        return questionnaireList;
    }



}
