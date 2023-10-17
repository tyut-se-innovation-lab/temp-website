package tyut.selab.rule.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.service.RuleService;

import java.util.List;

/**
 * 奖惩制度管理
 */
@Api("奖惩制度管理")
@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    /**
     * 根据rule_score中的rule_status字段查询是否需要弹出规章制度
     *
     * @param userId
     * @return
     */
    //@PreAuthorize("@ss.hasAnyPermi('rule:content')")
    @ApiOperation("根据rule_score中的rule_status字段查询是否需要弹出规章制度")
    @GetMapping("/getRuleStatus/{userId}")
    public AjaxResult getRuleStatus(@PathVariable("userId") Long userId) {
        return AjaxResult.success(ruleService.getRuleStatus(userId));
    }

    /**
     * 当用户点击不再提示奖惩制度窗口后，发送请求修改rule_score表中的rule_status字段
     *
     * @param userId
     * @param ruleStatus
     */
    // @ApiOperation("当用户点击不再提示奖惩制度窗口后，发送请求修改rule_score表中的rule_status字段")
    @PutMapping("/setRuleStatus")
    public AjaxResult setRuleStatus(Long userId, int ruleStatus) {
        ruleService.setRuleStatus(userId, ruleStatus);
        return AjaxResult.success();
    }

    /**
     * 查询是否需要弹出操作弹窗
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    @ApiOperation("查询是否需要弹出操作弹窗")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult getOperationStatus(@PathVariable Long userId) {
        return AjaxResult.success(ruleService.getOperationStatus(userId));
    }

    /**
     * 查询需要弹出的操作信息
     *
     * @return
     */
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    @GetMapping("/getOperationInfo/{userId}")
    public AjaxResult getOperationInfo(@PathVariable Long userId) {
        List<OperationVO> operationInfo = ruleService.getOperationInfo(userId);
        return AjaxResult.success(operationInfo);
    }
}
