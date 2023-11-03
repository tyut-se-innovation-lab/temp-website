package tyut.selab.vote.domain.po;

/**
 * @className: OpinionContent
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/22 10:04
 * @version: 1.0
 */
public class VoteOpinionContent {
    private String userId;
    private String userName;
    private String content;
    private String isAnonymous;

    public VoteOpinionContent() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "VoteOpinionContent{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", isAnonymous='" + isAnonymous + '\'' +
                '}';
    }
}
