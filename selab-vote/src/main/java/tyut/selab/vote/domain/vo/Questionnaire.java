package tyut.selab.vote.domain.vo;

import java.util.Date;
import java.util.List;

/**
 * 问卷
 * @author Big_bai on 2022/11/19
 */
public class Questionnaire {
    //标题
    private String title;
    //简介
    private String content;
    //截止时间
    private Date deadline;
    //内容
    private List<VoteQue> voteQues;

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
}
