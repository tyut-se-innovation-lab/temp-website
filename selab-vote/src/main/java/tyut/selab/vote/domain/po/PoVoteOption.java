package tyut.selab.vote.domain.po;

import tyut.selab.vote.enums.VoteOptionType;

public class PoVoteOption {
    private Long id;
    private Long parentId;
    private Long voteId;
    private String optionType;
    private String content;
    private String percentage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "PoVoteOption{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", voteId=" + voteId +
                ", optionType='" + optionType + '\'' +
                ", content='" + content + '\'' +
                ", percentage='" + percentage + '\'' +
                '}';
    }
}
