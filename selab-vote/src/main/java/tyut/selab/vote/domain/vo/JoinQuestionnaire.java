package tyut.selab.vote.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * 用户可以参与的投票粗略信息
 * @author Big-Bai
 **/
@Data
public class JoinQuestionnaire {
    /**
     * 投票id
     */
    private Long id;
    /**
     * 投票标题
     */
    private String title;
    /**
     * 投票状态
     */
    private String state;
    /**
     * 开始时间
     */
    private Date start;
    /**
     * 结束时间
     */
    private Date end;
    /**
     * 简介
     */
    private String content;
    /**
     * 是否已经参与
     */
    private Boolean isJoin;
}
