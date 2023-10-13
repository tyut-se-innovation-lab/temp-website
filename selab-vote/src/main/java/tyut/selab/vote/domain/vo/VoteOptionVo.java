package tyut.selab.vote.domain.vo;


import lombok.Data;
import tyut.selab.vote.enums.VoteOptionType;

/**
 * @className: VoteOptionVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/9 19:46
 * @version: 1.0
 */
@Data
public class VoteOptionVo {
    private Long id;
    private Long voteId;
    private VoteOptionType optionType;
    private String content;

    private Double percentage;
    private Integer voteNum;
    // 是否选中
    private Boolean isSelect;

}
