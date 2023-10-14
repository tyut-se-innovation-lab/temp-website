package tyut.selab.vote.domain.po;

import lombok.Data;
import tyut.selab.vote.enums.VoteStatus;

import java.util.Date;

/**
 * @className: VoteRangeDTO
 * @author: lizhichao
 * @description: TODO 投票外界面展示粗略信息
 * @date: 2023/10/9 17:42
 * @version: 1.0
 */
@Data
public class VoteRange {

    private Long voteId;
    private String title;
    private String content;
    private VoteStatus voteStatus;
    private Date createTime;
    private Date deadTime;
    private String userName;
    private Long userId;
}
