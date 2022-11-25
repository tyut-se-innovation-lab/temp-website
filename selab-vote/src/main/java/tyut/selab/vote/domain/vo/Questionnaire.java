package tyut.selab.vote.domain.vo;

import java.util.Date;
import java.util.List;

/**
 * 问卷
 * @author Big_bai on 2022/11/19
 */
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
    //内容
    private List<VoteQue> voteQues;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<VoteQue> getVoteQues() {
        return voteQues;
    }

    public void setVoteQues(List<VoteQue> voteQues) {
        this.voteQues = voteQues;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", creatTime=" + creatTime +
                ", deadline=" + deadline +
                ", voteQues=" + voteQues +
                '}';
    }
}
