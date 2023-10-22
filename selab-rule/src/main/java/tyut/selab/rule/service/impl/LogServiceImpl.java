package tyut.selab.rule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.mapper.RuleLogMapper;
import tyut.selab.rule.mapper.ScoreMapper;
import tyut.selab.rule.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private RuleLogMapper ruleLogMapper;
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public int deleteLog(Long userId, Long logId) {
        int scoreChange = ruleLogMapper.getByRuleLogId(logId);
        scoreMapper.updateScore(-scoreChange, userId);
        return ruleLogMapper.deleteByPrimaryKey(logId);
    }

    @Override
    public List<OperationVO> selectAllLog() {
        return ruleLogMapper.selectAllLog();
    }
}
