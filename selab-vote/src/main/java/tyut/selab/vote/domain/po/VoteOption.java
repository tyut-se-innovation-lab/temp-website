package tyut.selab.vote.domain.po;

import tyut.selab.vote.enums.VoteOptionType;

public class VoteOption {
    private Long id;
    private Long parentId;
    private Long voteId;
    private VoteOptionType optionType;
    private String content;

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

    public VoteOptionType getOptionType() {
        return optionType;
    }

    public void setOptionType(VoteOptionType optionType) {
        this.optionType = optionType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
