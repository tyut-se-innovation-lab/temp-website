package tyut.selab.vote.domain.po;


import lombok.Data;

/**
 * @className: VoteWeightVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/8 11:27
 * @version: 1.0
 */
@Data
public class VoteWeight {
    private Long voteId;
    private Long roleId;
    private String roleName;
    private Integer weight;
}
