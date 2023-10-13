package tyut.selab.vote.domain.po;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tyut.selab.vote.domain.vo.VoteOptionDetails;
import tyut.selab.vote.domain.vo.VoteOptionVo;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.enums.VoteType;

import java.util.Date;
import java.util.List;

/**
 * @className: VoteResultDetails
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/11 20:12
 * @version: 1.0
 */
@Data
public class VoteResultDetails {
    private Long voteId;
    private String userName;
    private Long userId;
    private VoteType type;
    private String title;
    private String content;
    private VoteStatus status;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadTime;
    // 投票选项
    private List<VoteOptionDetails> voteOptionDetails;
    private List<VoteReport> voteReports;
    // 投票权重
    private List<VoteWeight>  voteWeights;
}
