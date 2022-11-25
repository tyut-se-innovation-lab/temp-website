package tyut.selab.vote.domain.vo;

import lombok.Data;
import tyut.selab.vote.enums.VoteOptionType;

import java.util.List;

/**
 * 问题
 * @author Big_bai on 2022/11/19
 */
@Data
public class VoteQue {
    //id
    private long id;
    //类型
    private String type;
    //问题内容
    private String queContent;
    //选项
    List<VoteOption> options;


    @Override
    public String toString() {
        return "VoteQue{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", queContent='" + queContent + '\'' +
                ", options=" + options +
                '}';
    }
}
