package tyut.selab.rule.domain;

import java.io.Serializable;

/**
 * 人员分数
 * @TableName rule_score
 */
public class RuleScore implements Serializable {
    /**
     * 主键
     */
    private Long scoreId;

    /**
     * 每个被执行用户的id
     */
    private Long userId;

    /**
     * 记录被执行人的分数
     */
    private Long scores;

    /**
     * 是否弹出规章制度 0为不弹出 1为弹出 默认为0
     */
    private Integer ruleStatus;

    /**
     * 近期对其有没有经行操作 0为没有 1为有 0为默认值
     */
    private Integer operationStatus;

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    public Long getScoreId() {
        return scoreId;
    }

    /**
     * 主键
     */
    public void setScoreId(Long scoreId) {
        this.scoreId = scoreId;
    }

    /**
     * 每个被执行用户的id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 每个被执行用户的id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 记录被执行人的分数
     */
    public Long getScores() {
        return scores;
    }

    /**
     * 记录被执行人的分数
     */
    public void setScores(Long scores) {
        this.scores = scores;
    }

    /**
     * 是否弹出规章制度 0为不弹出 1为弹出 默认为0
     */
    public Integer getRuleStatus() {
        return ruleStatus;
    }

    /**
     * 是否弹出规章制度 0为不弹出 1为弹出 默认为0
     */
    public void setRuleStatus(Integer ruleStatus) {
        this.ruleStatus = ruleStatus;
    }

    /**
     * 近期对其有没有经行操作 0为没有 1为有 0为默认值
     */
    public Integer getOperationStatus() {
        return operationStatus;
    }

    /**
     * 近期对其有没有经行操作 0为没有 1为有 0为默认值
     */
    public void setOperationStatus(Integer operationStatus) {
        this.operationStatus = operationStatus;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RuleScore other = (RuleScore) that;
        return (this.getScoreId() == null ? other.getScoreId() == null : this.getScoreId().equals(other.getScoreId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getScores() == null ? other.getScores() == null : this.getScores().equals(other.getScores()))
            && (this.getRuleStatus() == null ? other.getRuleStatus() == null : this.getRuleStatus().equals(other.getRuleStatus()))
            && (this.getOperationStatus() == null ? other.getOperationStatus() == null : this.getOperationStatus().equals(other.getOperationStatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getScoreId() == null) ? 0 : getScoreId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getScores() == null) ? 0 : getScores().hashCode());
        result = prime * result + ((getRuleStatus() == null) ? 0 : getRuleStatus().hashCode());
        result = prime * result + ((getOperationStatus() == null) ? 0 : getOperationStatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scoreId=").append(scoreId);
        sb.append(", userId=").append(userId);
        sb.append(", scores=").append(scores);
        sb.append(", ruleStatus=").append(ruleStatus);
        sb.append(", operationStatus=").append(operationStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}