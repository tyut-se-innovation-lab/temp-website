package tyut.selab.rule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.mapper.RuleLogMapper;
import tyut.selab.rule.service.LogService;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private RuleLogMapper ruleLogMapper;
    @Override
    public int deleteLog(Long logId) {
        return ruleLogMapper.deleteByPrimaryKey(logId);
    }

    @Override
    public List<OperationVO> selectAllLog() {
        return ruleLogMapper.selectAllLog();
    }
}
