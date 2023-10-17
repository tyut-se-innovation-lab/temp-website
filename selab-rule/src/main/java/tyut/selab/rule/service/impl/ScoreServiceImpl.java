package tyut.selab.rule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.mapper.ScoreMapper;
import tyut.selab.rule.service.ScoreService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 分数管理
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    /**
     * 加减分数
     *
     * @param scoreChange
     * @param userId
     */
    @Override
    public void addOrReduceScore(Integer scoreChange, Long userId) {
        if(scoreChange == null){
            scoreChange = 0;
        }
        Integer score = scoreMapper.getByUserId(userId);
        if (score == null) {
            score = 0;
        }
        score += scoreChange;
        scoreMapper.updateScore(score, userId);
    }

    /**
     * 该用户需要弹出弹窗
     *
     * @param i
     */
    @Override
    public void setUserPop(Integer i) {
        scoreMapper.setUserPop(1);
    }

    /**
     * 该操作需要弹出弹窗
     *
     * @param i
     */
    @Override
    public void setOperationPop(Integer i) {
        scoreMapper.setOperationPop(1);
    }
}
