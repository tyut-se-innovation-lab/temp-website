package tyut.selab.vote.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 问卷
 * @author Big_bai on 2022/11/19
 */
@Data
public class Questionnaire {
    //id
    private Long id;
    //标题
    private String title;
    //简介
    private String content;
    //状态
    private String status;
    //开始时间
    private Date creatTime;
    //截止时间
    private Date deadline;
    //总人数
    private int peoples;
    //内容
    private List<VoteQue> voteQues;
    //是否支持撤回
    private int isWithdraw;
    //是否参与
    private boolean isJoin;

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", creatTime=" + creatTime +
                ", deadline=" + deadline +
                ", peoples=" + peoples +
                ", voteQues=" + voteQues +
                '}';
    }
}
