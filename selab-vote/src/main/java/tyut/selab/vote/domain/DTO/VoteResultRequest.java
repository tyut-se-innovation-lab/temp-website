package tyut.selab.vote.domain.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @className: VoteResultRequest
 * @author: lizhichao
 * @description: TODO 参与投票后端接受对象
 * @date: 2023/10/18 19:01
 * @version: 1.0
 */

public class VoteResultRequest {
    private Long voteId;
    @NotBlank(message = "投票发布人不能为空")
    private String userId;
    // 选项id
    @NotNull(message = "投票选项不能为空")
    private List<Long> voteOptionIds;
    @NotBlank
    private String content;

    private Date createTime;

    // 是否实名（1 为匿名 0 为实名）
    private String isAnonymous;

    public VoteResultRequest() {

    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public List<Long> getVoteOptionIds() {
        return voteOptionIds;
    }

    public void setVoteOptionIds(List<Long> voteOptionIds) {
        this.voteOptionIds = voteOptionIds;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(String isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VoteResultRequest{" +
                "voteId=" + voteId +
                ", voteOptionIds=" + voteOptionIds +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", isAnonymous='" + isAnonymous + '\'' +
                '}';
    }
}
