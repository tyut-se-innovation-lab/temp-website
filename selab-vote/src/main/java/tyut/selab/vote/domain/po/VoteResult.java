package tyut.selab.vote.domain.po;

import lombok.Data;
import tyut.selab.vote.enums.User;

import java.util.Date;

@Data
public class VoteResult {
    private Long id;
    private Long voteOptionId;
    private String userId;
    private String content;
    private int isEnable;
    private Date createTime;
    private Integer weight;

    @Override
    public String toString() {
        return "VoteResult{" +
                "id=" + id +
                ", voteOptionId=" + voteOptionId +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", isEnable=" + isEnable +
                ", createTime=" + createTime +
                '}';
    }

}
