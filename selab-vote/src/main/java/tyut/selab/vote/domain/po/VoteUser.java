package tyut.selab.vote.domain.po;

public class VoteUser {
    private String userId;
    private Long voteId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public VoteUser() {
    }

    public VoteUser(String userId, Long voteId) {
        this.userId = userId;
        this.voteId = voteId;
    }

    @Override
    public String toString() {
        return "VoteUser{" +
                "userId='" + userId + '\'' +
                ", voteId=" + voteId +
                '}';
    }
}
