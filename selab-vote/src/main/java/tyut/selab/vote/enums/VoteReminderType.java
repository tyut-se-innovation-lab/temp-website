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

    MAXIMUMNUNREPORTForAdmin("1","有一个投票因举报数量上限冻结"),
    MAXIMUMNUNREPORTForLaunchUser("2","您发布的一个投票已被冻结"),
    CLOSEBYADMIN("3","您发布的一个投票已被关闭"),
    VOTEREPORT("4","有一个投票被举报"),
    VOTEWITHDRAW("5","有一个撤回请求投票"),
    NOPARTICIPATE("6","您有一个待参与投票"),
    FREEZERESULTBYWITHDRAW("7","您的撤回请求已处理"),
    FREEZERESULTBYREPORT("8","您举报的投票已被冻结"),
    FREEZERESULTCLOSEBYREPORT("9","您举报的投票已被关闭");

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
