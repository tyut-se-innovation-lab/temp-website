package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.service.IDisplayAllVote;

import java.util.List;

@Service
public class DisplayAllVoteImpl implements IDisplayAllVote {
    @Autowired
    DisplayAllVoteMapper displayAllVoteMapper;
    @Override
    public List<VoteInfo> displayAllVote() {
        List<VoteInfo> voteInfos = displayAllVoteMapper.displayAllVote();
        return voteInfos;
    }
}
