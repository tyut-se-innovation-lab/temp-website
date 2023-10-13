package tyut.selab.vote.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum VoteStatus {
    UNDERWAY(1, "进行中"),
    FINISH(2, "已结束"),
    FREEZE(3,"异常冻结"),
    WITHDRAW(4, "被撤回"),
    CLOSED(5,"已关闭");
    @JsonValue
    private final int id;
    private final String remake;

    VoteStatus(int id, String remake) {
        this.id = id;
        this.remake = remake;
    }

    public int getId() {
        return id;
    }

    public String getRemake() {
        return remake;
    }
}
