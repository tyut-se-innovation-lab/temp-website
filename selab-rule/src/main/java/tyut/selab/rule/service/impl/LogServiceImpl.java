package tyut.selab.rule.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
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
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public int deleteLog(Long userId, Long logId) {
        int scoreChange = ruleLogMapper.getByRuleLogId(logId);
        scoreMapper.updateScore(-scoreChange, userId);
        return ruleLogMapper.deleteByPrimaryKey(logId);
    }

    @Override
    public List<OperationVO> selectAllLog() {
        List<OperationVO> operationVOS = ruleLogMapper.selectAllLog();
        for (OperationVO operationVO : operationVOS) {
            Long userId = Long.valueOf(operationVO.getTargetUserId());
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            operationVO.setNickName(sysUser.getNickName());
        }
        return operationVOS;
    }
}
