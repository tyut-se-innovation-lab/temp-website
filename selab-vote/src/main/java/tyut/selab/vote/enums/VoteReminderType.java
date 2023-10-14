package tyut.selab.vote.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @className: VoteReminderType
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/12 16:41
 * @version: 1.0
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum VoteReminderType {

     MAXIMUMNUNREPORT("1","举报数量上限冻结"),
    VOTEREPORT("2","投票被举报"),
    VOTEWITHDRAW("3","撤回投票提醒"),
    NOPARTICIPATE("4","待参与投票提醒"),
    FREEZERESULT("5","冻结处理结果提醒"),
    VOTECLOSEDEND("6","投票即将结束提醒"),
    VOTEEND("7","投票即将结束提醒");

    @JsonValue
    private final String sign;
    private final String remake;

    VoteReminderType(String sign, String remake) {
        this.sign = sign;
        this.remake = remake;
    }

    public String getSign() {
        return sign;
    }

    public String getRemake() {
        return remake;
    }
}
