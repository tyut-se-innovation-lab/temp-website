package tyut.selab.vote.domain.po;



import java.util.Date;
public class VoteResult {
    private Long id;
    private Long voteId;
    private Long voteOptionId;
    private String userName;
    private Long userId;

    private String content;
    private Date createTime;
   // 1 为匿名 0 为实名
    // 是否实名（1 为匿名 0 为实名）
    private String isAnonymous;

    private Integer weight;


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


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
