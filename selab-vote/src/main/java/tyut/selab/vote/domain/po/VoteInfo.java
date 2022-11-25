package tyut.selab.vote.domain.po;

import lombok.Data;
import tyut.selab.vote.enums.VoteStatus;

import java.util.Date;

@Data
public class VoteInfo {
    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String status;
    private Date deadline;
    private Date createTime;
    private Long weight;
}

