package tyut.selab.vote.domain.po;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tyut.selab.vote.enums.VoteReminderType;
import tyut.selab.vote.enums.VoteStatus;

import java.util.Date;

/**
 * @className: VoteReminderDTO
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/12 20:15
 * @version: 1.0
 */
@Data
public class VoteReminder {
    private Long voteId;
    private String title;
    private String content;
    private VoteStatus voteStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date deadTime;
    private String userName;
    private Long userId;
    private VoteReminderType voteReminderType;
}
