package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.mapper.DisplayMyHistoryVoteMapper;
import tyut.selab.vote.service.IDisplayMyHistoryVoteService;

import java.util.List;

@Service
public class DisplayMyHistoryVoteServiceImpl implements IDisplayMyHistoryVoteService {
    @Autowired
    DisplayMyHistoryVoteMapper myHistoryVoteMapper;

    @Override
    public List<VoteResult> displayMyHistory() {
        List<VoteResult> list = myHistoryVoteMapper.displayMyHistory();

        return list;
    }
}
