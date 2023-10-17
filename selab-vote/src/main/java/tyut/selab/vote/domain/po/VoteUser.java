package tyut.selab.vote.domain.po;

import lombok.Data;
import lombok.ToString;

/**
 * @className: VoteUser
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 19:22
 * @version: 1.0
 */
@Data
public class VoteUser {

    private Long voteId;
    private Long userId;
    private String userName;
    // 是否完成投票 1为完成 0为未完成
    private Integer isComplete;

    public VoteUser(Long voteId, Long userId, String userName, Integer isComplete) {
        this.voteId = voteId;
        this.userId = userId;
        this.userName = userName;
        this.isComplete = isComplete;
    }

    public VoteUser() {
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

    @Override
    public String toString() {
        return "VoteUser{" +
                "voteId=" + voteId +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }
}
