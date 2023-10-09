package tyut.selab.rule.service;

import tyut.selab.rule.domain.VO.OperationVO;

import java.util.List;

/**
 * 奖惩制度业务层
 */
public interface RuleService {
    /**
     * 查询是否弹出奖惩制度
     * @param userId
     * @return
     */
    public int getRuleStatus(Long userId);

    /**
     * 当用户点击不再提示奖惩制度窗口后，发送请求修改rule_score表中的rule_status字段
     * @param userId
     * @param ruleStatus
     */
    public void setRuleStatus(Long userId,int ruleStatus);

    /**
     * 查询是否需要弹出操作弹窗
     * @param userId
     * @return
     */
    Integer getOperationStatus(Long userId);

    /**
     * 查询需要弹出的操作信息
     * @param userId
     * @return
     */
    List<OperationVO> getOperationInfo(Long userId);
}
