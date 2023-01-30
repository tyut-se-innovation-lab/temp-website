package tyut.selab.vote.domain.po;

import lombok.Data;

import java.util.Date;

@Data
public class VoteResult {
    private Long id;
    private Long voteOptionId;
    private String userId;
    private String content;
    private int isEnable;
    private Date createTime;
}
