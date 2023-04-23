package tyut.selab.vote.domain.po;

import tyut.selab.vote.enums.User;

import java.util.Date;

public class VoteResult {
    private Long id;
    private Long voteOptionId;
    private String userId;
    private User userIds;
    private String content;
    private int isEnable;
    private Date createTime;

    @Override
    public String toString() {
        return "VoteResult{" +
                "id=" + id +
                ", voteOptionId=" + voteOptionId +
                ", userId='" + userId + '\'' +
                ", userIds=" + userIds +
                ", content='" + content + '\'' +
                ", isEnable=" + isEnable +
                ", createTime=" + createTime +
                '}';
    }

    public User getUserIds() {
        return userIds;
    }

    public void setUserIds(User userIds) {
        this.userIds = userIds;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoteOptionId() {
        return voteOptionId;
    }

    public void setVoteOptionId(Long voteOptionId) {
        this.voteOptionId = voteOptionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(int isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
