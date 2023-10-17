package tyut.selab.rule.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 奖惩制度的日志记载
 * @TableName rule_log
 */
public class RuleLog implements Serializable{
    /**
     * 
     */
    private Long ruleLogId;

    /**
     * 目标人的id
     */
    private Long targetUserId;

    /**
     * 原因叙述
     */
    private String reasonContent;

    /**
     * 
     */
    private Date createTime;

    /**
     * 创建人
     */
    private Long createUserId;

    /**
     * 是否查看过信息
     */
    private Integer status;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Long updateUserId;

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getRuleLogId() {
        return ruleLogId;
    }

    /**
     * 
     */
    public void setRuleLogId(Long ruleLogId) {
        this.ruleLogId = ruleLogId;
    }

    /**
     * 目标人的id
     */
    public Long getTargetUserId() {
        return targetUserId;
    }

    /**
     * 目标人的id
     */
    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    /**
     * 原因叙述
     */
    public String getReasonContent() {
        return reasonContent;
    }

    /**
     * 原因叙述
     */
    public void setReasonContent(String reasonContent) {
        this.reasonContent = reasonContent;
    }

    /**
     * 
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * 创建人
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 是否查看过信息
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 是否查看过信息
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 
     */
    public Long getUpdateUserId() {
        return updateUserId;
    }

    /**
     * 
     */
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
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
        RuleLog other = (RuleLog) that;
        return (this.getRuleLogId() == null ? other.getRuleLogId() == null : this.getRuleLogId().equals(other.getRuleLogId()))
            && (this.getTargetUserId() == null ? other.getTargetUserId() == null : this.getTargetUserId().equals(other.getTargetUserId()))
            && (this.getReasonContent() == null ? other.getReasonContent() == null : this.getReasonContent().equals(other.getReasonContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUserId() == null ? other.getCreateUserId() == null : this.getCreateUserId().equals(other.getCreateUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getUpdateUserId() == null ? other.getUpdateUserId() == null : this.getUpdateUserId().equals(other.getUpdateUserId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRuleLogId() == null) ? 0 : getRuleLogId().hashCode());
        result = prime * result + ((getTargetUserId() == null) ? 0 : getTargetUserId().hashCode());
        result = prime * result + ((getReasonContent() == null) ? 0 : getReasonContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUserId() == null) ? 0 : getCreateUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getUpdateUserId() == null) ? 0 : getUpdateUserId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", ruleLogId=").append(ruleLogId);
        sb.append(", targetUserId=").append(targetUserId);
        sb.append(", reasonContent=").append(reasonContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
