package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOption;
import tyut.selab.vote.service.ILaunchVoteService;

/**
 * @author Big_bai on 2022/11/19
 */
public class LaunchVoteService implements ILaunchVoteService {

    @Override
    public void LaunchVote(VoteInfo voteInfo, VoteOption voteOption, Long userId) {

    }
}
