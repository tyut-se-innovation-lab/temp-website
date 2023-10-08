package tyut.selab.vote.domain.vo;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;
import tyut.selab.vote.tools.GetSysTime;

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
    private Date createdTime;
    //截止时间
    private Date deadline;
    //权重表
    private Long authority;
    //是否撤回
    private boolean isWithdraw;
    //总人数
    private int peoples;
    //是否参与
    private boolean isJoin;
    //内容
    private List<VoteQue> voteQues;

    public boolean isEmpty(){
        return voteQues.isEmpty();
    }

    @JsonSetter("context")
    public void setContent(String content) {
        this.content = content;
    }

    @JsonSetter("deadline")
    public void setDeadline(String deadline) {
        this.deadline= GetSysTime.stringToDate(deadline);
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                ", deadline=" + deadline +
                ", authority=" + authority +
                ", isWithdraw=" + isWithdraw +
                ", peoples=" + peoples +
                ", isJoin=" + isJoin +
                ", voteQues=" + voteQues +
                '}';
    }
}
