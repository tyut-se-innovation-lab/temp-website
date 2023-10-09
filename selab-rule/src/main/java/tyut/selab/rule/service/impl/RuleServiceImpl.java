package tyut.selab.rule.service.impl;

import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.domain.entity.Operation;
import tyut.selab.rule.mapper.RuleMapper;
import tyut.selab.rule.service.RuleService;

import java.util.ArrayList;
import java.util.List;

/**
 * 奖惩制度 业务层处理
 */
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;

    /**
     * 查询是否需要弹出奖惩制度
     *
     * @param userId
     * @return
     */
    @Override
    public int getRuleStatus(Long userId) {
        return ruleMapper.getRuleStatus(userId);
    }

    /**
     * 当用户点击不再提示奖惩制度窗口后，发送请求修改rule_score表中的rule_status字段
     *
     * @param userId
     * @param ruleStatus
     */
    @Override
    public void setRuleStatus(Long userId, int ruleStatus) {
        ruleMapper.setRuleStatus(userId, ruleStatus);
    }

    /**
     * 查询是否需要弹出操作弹框
     *
     * @param userId
     * @return
     */
    @Override
    public Integer getOperationStatus(Long userId) {
        Integer operationStatus = ruleMapper.getUserStatus(userId);
        if (operationStatus == 1) {
            Integer userStatus = ruleMapper.getOperationStatus(userId);
            if (userStatus == 1) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 查询需要弹出的操作信息
     *
     * @param userId
     * @return
     */
    @Override
    public List<OperationVO> getOperationInfo(Long userId) {
        List<Operation> operations = ruleMapper.getOperationInfo(userId);
        List<OperationVO> operationVOS = new ArrayList<>();
        for (int i = 0; i < operations.size(); i++) {
            operationVOS.get(i).setReason(operations.get(i).getReasonContent());
            operationVOS.get(i).setUser(operations.get(i).getUpdateUser());
            operationVOS.get(i).setTime(operations.get(i).getUpdateTime());
            operationVOS.get(i).setImage(operations.get(i).getImage());
        }
        return operationVOS;
    }
}
