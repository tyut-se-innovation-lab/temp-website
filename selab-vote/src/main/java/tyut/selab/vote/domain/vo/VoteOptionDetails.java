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
public class VoteOptionDetails {
    private Long id;
    private Long voteId;
    private VoteOptionType optionType;
    private String content;

    private Double percentage;
    private Integer voteNum;
    private List<String> userName;

}
