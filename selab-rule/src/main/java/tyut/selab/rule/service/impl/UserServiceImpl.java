package tyut.selab.rule.service.impl;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.RuleLog;
import tyut.selab.rule.domain.RuleScore;
import tyut.selab.rule.domain.VO.LogPageVO;
import tyut.selab.rule.domain.VO.LogVO;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.domain.VO.RuleVO;
import tyut.selab.rule.domain.entity.Operation;
import tyut.selab.rule.mapper.RuleLogMapper;
import tyut.selab.rule.mapper.RuleScoreMapper;
import tyut.selab.rule.service.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
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
        LocalDateTime end = LocalDateTime.now();
        Integer scoreChange = ruleScoreMapper.getScoreChangeByTime(begin, end, userId);
        if (scoreChange == null) {
            scoreChange = 0;
        }
        return scoreChange;
    }

    //查询用户当天的所有相关日志
    @Override
    public LogPageVO getLogForDay(Long userId, Integer pageNum, Integer pageSize, Date startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);

        // 将时间部分设置为零点
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // 获取修正后的时间
        Date start = calendar.getTime();
        // 将日期加一天
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        // 获取下一天的零点时间
        Date end = calendar.getTime();
        PageHelper.startPage(pageNum, pageSize);
        List<Operation> operations = ruleLogMapper.getLogForDay(userId, start, end);
        PageInfo<Operation> operationPageInfo = new PageInfo<>(operations);
        LogPageVO logVO = new LogPageVO();
        logVO.setTotal(operationPageInfo.getTotal());
        logVO.setList(operationPageInfo.getList());
        return logVO;
    }

    /**
     * 查询用户当天的增减分操作日志
     *
     * @param userId
     */
    @Override
    public List<OperationVO> getScoreChangeOperationsForDay(Long userId) {
        //当前时间的分数-昨天晚上23：59：59秒的分数即当天的增减分操作日志
        LocalDateTime begin = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.now();
        List<OperationVO> logVOList = ruleScoreMapper.getScoreChangeOperationsByTime(begin, end, userId);
        return logVOList;
    }

    /**
     * 查询用户当月的增减分操作日志
     *
     * @param userId
     * @return
     */
    @Override
    public List<OperationVO> getScoreChangeOperationsForMonth(Long userId) {
        //当前时间的分数-本月第一天00：00：00的分数即当天的增减分操作日志
        LocalDateTime begin = LocalDateTime.of(LocalDate.now().withDayOfMonth(1), LocalTime.MIN);
        LocalDateTime end = LocalDateTime.now();
        List<OperationVO> logVOList = ruleScoreMapper.getScoreChangeOperationsByTime(begin, end, userId);
        return logVOList;
    }

    /**
     * 查询当前用户的所有操作日志
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public LogVO getAllLogs(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<OperationVO> page = ruleLogMapper.getAllLogs(userId);
        return new LogVO((int) page.getTotal(), page.getResult());
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
        if (scoreChange == null) {
            scoreChange = 0;
        }
        return scoreChange;
    }
}
