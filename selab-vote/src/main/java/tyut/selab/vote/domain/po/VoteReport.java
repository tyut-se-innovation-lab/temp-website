package tyut.selab.vote.domain.po;


import lombok.Data;

/**
 * @className: VoteReport
 * @author: lizhichao
 * @description: TODO  投票举报实体类
 * @date: 2023/10/3 18:23
 * @version: 1.0
 */
@Data
public class VoteReport {
    private Long reportId;
    // 举报人
    private Long user_id;
    private Long voteId;
    private String description;
}
