package tyut.selab.vote.domain.vo;

import tyut.selab.vote.enums.VoteOptionType;

import java.util.List;

/**
 * @className: VoteOptionDetails
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 20:14
 * @version: 1.0
 */
public class VoteOptionDetailsVo {
    private Long id;
    private Long voteId;
    private VoteOptionType optionType;
    private String content;

    private String percentage;
    private Integer chooseNum;
    private List<String> userNames;

    public VoteOptionDetailsVo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getVoteNum() {
        return chooseNum;
    }

    public void setVoteNum(Integer chooseNum) {
        this.chooseNum = chooseNum;
    }

    public List<String> getUserName() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    @Override
    public String toString() {
        return "VoteOptionDetails{" +
                "id=" + id +
                ", voteId=" + voteId +
                ", optionType=" + optionType +
                ", content='" + content + '\'' +
                ", percentage=" + percentage +
                ", voteNum=" + chooseNum +
                ", userName=" + userNames +
                '}';
    }
}
