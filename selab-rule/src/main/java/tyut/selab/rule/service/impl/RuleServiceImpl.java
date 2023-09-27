package tyut.selab.rule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.mapper.RuleMapper;
import tyut.selab.rule.service.RuleService;

/**
 * 奖惩制度 业务层处理
 */
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public int getStatus(Long userId) {
        return ruleMapper.getStatus(userId);
    }

    @Override
    public void setStatus(Long userId, int ruleStatus) {
        ruleMapper.setStatus(userId, ruleStatus);
    }

    @Override
    public void edit(String text) {
        ruleMapper.edit(text);
    }

    @Override
    public String getText() {
        return ruleMapper.getText();
    }
}
