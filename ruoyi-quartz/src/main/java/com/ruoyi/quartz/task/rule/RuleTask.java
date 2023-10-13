package com.ruoyi.quartz.task.rule;

import org.springframework.stereotype.Component;
import tyut.selab.rule.service.RuleService;
import tyut.selab.rule.service.impl.RuleServiceImpl;

import javax.annotation.Resource;

/**
 * 奖惩制度系统的定时任务
 * @author Otion
 * @date 2023/10/10
 */
@Component("rule")
public class RuleTask {

    @Resource
    private RuleServiceImpl ruleService;

    /**
     *每两个月重置奖惩制度系统中的评分
     */
    public void scoreClean(){
        ruleService.clearAllScore();
    }
}
