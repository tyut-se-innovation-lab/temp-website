package tyut.selab.rule.service.impl;


import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.RuleLog;
import tyut.selab.rule.domain.RuleScore;
import tyut.selab.rule.domain.VO.RuleVO;
import tyut.selab.rule.mapper.RuleLogMapper;
import tyut.selab.rule.mapper.RuleScoreMapper;
import tyut.selab.rule.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private RuleLogMapper ruleLogMapper;
    @Autowired
    private RuleScoreMapper ruleScoreMapper;

    /**
     * @return {@link R}
     * 查看用户的当前分数和与该用户相关的操作
     */
    @Override
    public AjaxResult userInfo(Long userId) {
        List<RuleScore> scores = ruleScoreMapper.selectByUserId(userId);
        RuleScore score = null;
        if (scores.size() != 1) {
            RuleScore newUser = new RuleScore();
            newUser.setUserId(userId);
            newUser.setScores(0L);
            newUser.setRuleStatus(1);
            newUser.setOperationStatus(0);
            ruleScoreMapper.insert(newUser);
            score = newUser;
        } else {
            score = scores.get(0);
        }
        log.info("we12");
        List<RuleLog> logs = ruleLogMapper.selectByUserId(userId);
        RuleVO ruleVO = new RuleVO();
        ruleVO.setRuleScore(score.getScores());
        ruleVO.setLogs(logs);
        ruleVO.setUserId(userId);
        return AjaxResult.success(ruleVO);
    }

    /**
     * 查询用户当月的增减分情况
     *
     * @param userId
     * @return
     */
    @Override
    public Integer getScoreChangeForMonth(Long userId) {
        //当前时间的分数-本月第一天00：00：00的分数即当天的增减分情况
        LocalDateTime begin = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.MAX);
        Integer scoreChange = ruleScoreMapper.getScoreChangeByTime(begin, end, userId);
        if(scoreChange == null){
            scoreChange = 0;
        }
        return scoreChange;
    }

    /**
     * 查询用户当天的增减分情况
     *
     * @param userId
     * @return
     */
    public Integer getScoreChangeForDay(Long userId) {
        //当前时间的分数-昨天晚上23：59：59秒的分数即当天的增减分情况
        LocalDateTime begin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.now();
        Integer scoreChange = ruleScoreMapper.getScoreChangeByTime(begin, end, userId);
        if(scoreChange == null){
            scoreChange = 0;
        }
        return scoreChange;
    }
}
