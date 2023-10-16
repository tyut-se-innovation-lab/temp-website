package tyut.selab.vote.domain.DTO;


import lombok.Getter;
import tyut.selab.vote.enums.VoteOptionType;

import java.util.List;

/**
 * @className: VoteOptionVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/9 19:46
 * @version: 1.0
 */

public class VoteOptionLaunchDTO {

    private Long id;

    private Long voteId;

    private VoteOptionType optionType;

    private String content;

    private String percentage;
    // 选择数量

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

    public void setPercentage(String percentage) {
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

    public VoteOptionLaunchDTO() {
    }

    public Long getId() {
        return id;
    }

    public Long getVoteId() {
        return voteId;
    }

    public VoteOptionType getOptionType() {
        return optionType;
    }

    public String getContent() {
        return content;
    }

    public String getPercentage() {
        return percentage;
    }

    public Integer getChooseNum() {
        return chooseNum;
    }

    @Override
    public String toString() {
        return "VoteOptionVo{" +
                "id=" + id +
                ", voteId=" + voteId +
                ", optionType=" + optionType +
                ", content='" + content + '\'' +
                ", percentage='" + percentage + '\'' +
                ", chooseNum=" + chooseNum +
                ", isSelect=" + isSelect +
                '}';
    }
}
