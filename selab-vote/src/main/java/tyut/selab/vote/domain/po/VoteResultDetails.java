package tyut.selab.vote.domain.po;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tyut.selab.vote.domain.vo.VoteOptionDetailsVo;
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
    private VoteType voteType;
    private String title;
    private String content;
    private VoteStatus status;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadTime;
    // 是否实时
    private String isRealTime;
    // 投票选项
    private List<VoteOptionDetailsVo> voteOptionDetails;

    // 投票权重
    private List<VoteWeight>  voteWeights;
}
