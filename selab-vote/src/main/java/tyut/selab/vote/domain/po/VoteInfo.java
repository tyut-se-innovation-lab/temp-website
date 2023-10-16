package tyut.selab.vote.domain.po;


import com.fasterxml.jackson.annotation.JsonFormat;

import tyut.selab.vote.domain.DTO.VoteOptionLaunchDTO;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.enums.VoteType;

import java.util.Date;
import java.util.List;

/**
 * @className: VoteInfoVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/7 17:18
 * @version: 1.0
 */

public class VoteInfo {
    private Long voteId;
    private String userName;
    private Long userId;
    private VoteType voteType;
    private String title;
    private String content;
    private VoteStatus status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadTime;
    private Integer optionNum;
    // 1 为实时 0 为非实时
    private String isRealTime;
    // 投票选项
    private List<VoteOptionLaunchDTO> voteOptionLaunchDTOs;
    // 是否完成投票 0为未参与  1为已参与
    private String isComplete;
    // 投票权重
    private List<VoteWeight>  voteWeights;
    // 删除标志（0为删除 1为否）
    private String delFlag;

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public VoteStatus getStatus() {
        return status;
    }

    public void setStatus(VoteStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public Integer getOptionNum() {
        return optionNum;
    }

    public void setOptionNum(Integer optionNum) {
        this.optionNum = optionNum;
    }

    public String getIsRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(String isRealTime) {
        this.isRealTime = isRealTime;
    }

    public List<VoteOptionLaunchDTO> getVoteOptionVoList() {
        return voteOptionLaunchDTOs;
    }

    public void setVoteOptionVoList(List<VoteOptionLaunchDTO> voteOptionLaunchDTOs) {
        this.voteOptionLaunchDTOs = voteOptionLaunchDTOs;
    }

    public String getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(String isComplete) {
        this.isComplete = isComplete;
    }

    public List<VoteWeight> getVoteWeights() {
        return voteWeights;
    }

    public void setVoteWeights(List<VoteWeight> voteWeights) {
        this.voteWeights = voteWeights;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public VoteInfo() {
    }

    @Override
    public String toString() {
        return "VoteInfo{" +
                "voteId=" + voteId +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", voteType=" + voteType +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status=" + status +
                ", createTime=" + createTime +
                ", deadTime=" + deadTime +
                ", optionNum=" + optionNum +
                ", isRealTime='" + isRealTime + '\'' +
                ", voteOptionVoList=" + voteOptionLaunchDTOs +
                ", isComplete='" + isComplete + '\'' +
                ", voteWeights=" + voteWeights +
                ", delFlag='" + delFlag + '\'' +
                '}';
    }
}
