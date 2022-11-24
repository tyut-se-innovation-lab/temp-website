package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
import tyut.selab.vote.service.ILaunchVoteService;

/**
 * @author Big_bai on 2022/11/19
 */
public class LaunchVoteService implements ILaunchVoteService {
    @Autowired
    InsertInfoDBMapper  insertInfoDBMapper;
    @Override
    public void LaunchVote(VoteInfo voteInfo, PoVoteOption voteOption, Long userId) {

    }
}
