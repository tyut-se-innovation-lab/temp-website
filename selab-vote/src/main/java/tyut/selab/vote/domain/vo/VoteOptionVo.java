package tyut.selab.vote.domain.vo;


import lombok.Data;
import lombok.Getter;
import tyut.selab.vote.enums.VoteOptionType;

/**
 * @className: VoteOptionVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/9 19:46
 * @version: 1.0
 */

public class VoteOptionVo {
    @Getter
    private Long id;
    @Getter
    private Long voteId;
    @Getter
    private VoteOptionType optionType;
    @Getter
    private String content;
    @Getter
    private Double percentage;
    // 选择数量
    @Getter
    private Integer chooseNum;
    // 是否选中
    private Boolean isSelect;

    public void setId(Long id) {
        this.id = id;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public void setOptionType(VoteOptionType optionType) {
        this.optionType = optionType;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public void setChooseNum(Integer chooseNum) {
        this.chooseNum = chooseNum;
    }

    public Boolean getSelect() {
        return isSelect;
    }

    public void setSelect(Boolean select) {
        isSelect = select;
    }

    public VoteOptionVo() {
    }

    @Override
    public String toString() {
        return "VoteOptionVo{" +
                "id=" + id +
                ", voteId=" + voteId +
                ", optionType=" + optionType +
                ", content='" + content + '\'' +
                ", percentage=" + percentage +
                ", chooseNum=" + chooseNum +
                ", isSelect=" + isSelect +
                '}';
    }
}
