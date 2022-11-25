package tyut.selab.vote.domain.vo;

import tyut.selab.vote.enums.VoteOptionType;

import java.util.List;

/**
 * 问题
 * @author Big_bai on 2022/11/19
 */
public class VoteQue {
    //id
    private long id;
    //类型
    private String type;
    //问题内容
    private String queContent;
    private Long parentId;
    //选项
    List<VoteOption> options;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQueContent() {
        return queContent;
    }

    public void setQueContent(String queContent) {
        this.queContent = queContent;
    }

    public List<VoteOption> getOptions() {
        return options;
    }

    public void setOptions(List<VoteOption> options) {
        this.options = options;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "VoteQue{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", queContent='" + queContent + '\'' +
                ", parentId=" + parentId +
                ", options=" + options +
                '}';
    }
}
