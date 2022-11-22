package tyut.selab.vote.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.service.IDisplayVoteResultService;

import java.util.List;

@Service
public class DisplayVoteResultServiceImpl implements IDisplayVoteResultService {
    @Autowired
    DisplayVoteResultMapper voteResultMapper;

    public List<VoteResult> displayVoteResult() {
        List<VoteResult> voteResults = voteResultMapper.displayVoteRes();
        System.out.println(voteResults.get(0).getUserId());
        return voteResults;
    }
}
