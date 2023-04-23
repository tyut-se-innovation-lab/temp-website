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
    //用户是否选择   上传时默认为0
    private Boolean isSelect;
    //类型
    private String type;
    //文本框的值（有的话）
    private String other;
    //百分比
    private String percentage;

    @Override
    public String toString() {
        return "VoteOption{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", isSelect=" + isSelect +
                ", type='" + type + '\'' +
                ", other='" + other + '\'' +
                ", percentage='" + percentage + '\'' +
                '}';
    }
}
