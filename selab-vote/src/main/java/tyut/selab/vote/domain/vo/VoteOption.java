package tyut.selab.vote.domain.vo;

import lombok.Data;

/**
 * 选项
 * @author Big_bai on 2022/11/19
 */
@Data
public class VoteOption {
    //id
    private long id;
    //内容
    private String content;
    //类型
    private String type;
    //百分比
    private String percentage;

    @Override
    public String toString() {
        return "VoteOption{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", percentage='" + percentage + '\'' +
                '}';
    }
}
