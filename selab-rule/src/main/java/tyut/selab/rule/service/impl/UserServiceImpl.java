package tyut.selab.rule.service.impl;


import com.ruoyi.common.core.domain.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.rule.domain.RuleLog;
import tyut.selab.rule.domain.RuleScore;
import tyut.selab.rule.domain.RuleVO;
import tyut.selab.rule.mapper.RuleLogMapper;
import tyut.selab.rule.mapper.RuleScoreMapper;
import tyut.selab.rule.service.UserService;

import java.util.List;

@Service
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
    public R userInfo(Long userId) {
        RuleScore score = ruleScoreMapper.selectByUserId(userId);
        List<RuleLog> logs = ruleLogMapper.selectByUserId(userId);
        RuleVO ruleVO = new RuleVO();
        ruleVO.setRuleScore(score.getScores());
        ruleVO.setLogs(logs);
        ruleVO.setUserId(userId);
        return R.ok(ruleVO);
    }
}
