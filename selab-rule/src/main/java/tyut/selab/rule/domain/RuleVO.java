package tyut.selab.rule.domain;

import java.io.Serializable;
import java.util.List;

public class RuleVO implements Serializable {
    private Long userId;
    private Long ruleScore;
    private List<RuleLog> logs;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRuleScore() {
        return ruleScore;
    }

    public void setRuleScore(Long ruleScore) {
        this.ruleScore = ruleScore;
    }

    public List<RuleLog> getLogs() {
        return logs;
    }

    @Override
    public String toString() {
        return "RuleVO{" +
                "userId=" + userId +
                ", ruleScore=" + ruleScore +
                ", logs=" + logs +
                '}';
    }

    public void setLogs(List<RuleLog> logs) {
        this.logs = logs;
    }
}
