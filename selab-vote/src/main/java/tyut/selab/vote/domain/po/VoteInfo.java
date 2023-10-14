package tyut.selab.vote.domain.po;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import tyut.selab.vote.domain.vo.VoteOptionVo;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.enums.VoteType;

import java.util.Date;
import java.util.List;

/**
 * @className: VoteInfoVo
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/7 17:18
 * @version: 1.0
 */
@Data
public class VoteInfo {
    private Long voteId;
    private String userName;
    private Long userId;
    private VoteType voteType;
    private String title;
    private String content;
    private VoteStatus status;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date deadTime;
    private Integer optionNum;
    // 1 为实时 0 为非实时
    private String isRealTime;
    // 投票选项
    private List<VoteOptionVo> voteOptionVoList;
    // 是否完成投票 0为未参与  1为已参与
    private String isComplete;
    // 投票权重
    private List<VoteWeight>  voteWeights;
    // 删除标志（0为删除 1为否）
    private String delFlag;
}
