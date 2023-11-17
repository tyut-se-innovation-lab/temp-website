package tyut.selab.rule.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.mapper.SysUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.VO.LogVO;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.domain.entity.Operation;
import tyut.selab.rule.mapper.RuleLogMapper;
import tyut.selab.rule.mapper.ScoreMapper;
import tyut.selab.rule.service.LogService;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        Integer score = scoreMapper.getByUserId(userId);
        scoreMapper.updateScore(score - scoreChange, userId);
        return ruleLogMapper.deleteByPrimaryKey(logId);
    }

    @Override
    public String selectUserById(Long userId) {
        SysUser sysUser = sysUserMapper.selectUserById(userId);
        return sysUser.getNickName();
    }

    @Override
    public LogVO selectAllLog(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Operation> operations = ruleLogMapper.selectAllLog();
        PageInfo<Operation> pageInfo = new PageInfo<>(operations);
        List<OperationVO> operationVOS = new ArrayList<>();
        for (Operation operation : pageInfo.getList()) {
            OperationVO operationVO = new OperationVO();
            BeanUtils.copyProperties(operation, operationVO);
            Long userId = operation.getTargetUserId();
            SysUser sysUser = sysUserMapper.selectUserById(userId);
            operationVO.setNickName(sysUser.getNickName());
            operationVOS.add(operationVO);
        }
        LogVO logVO = new LogVO();
        logVO.setTotal((int) pageInfo.getTotal());
        logVO.setList(operationVOS);
        return logVO;
    }
}
