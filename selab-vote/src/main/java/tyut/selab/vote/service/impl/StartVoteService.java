package tyut.selab.vote.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.mapper.IStartVoteMapper;
import tyut.selab.vote.service.IStartVoteService;

/**
 * @author Big_bai on 2022/11/19
 */
@Service
public class StartVoteService implements IStartVoteService {
    @Autowired
    IStartVoteMapper startVoteMapper;
    @Override
    public void initAVote(Questionnaire questionnaire, Long userId) {

    }
}
