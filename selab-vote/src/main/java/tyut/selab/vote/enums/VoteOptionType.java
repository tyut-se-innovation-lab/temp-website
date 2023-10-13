package tyut.selab.vote.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum VoteOptionType {
    SINGLE_CHECKBOX("S", "单选框"),
    TEXT_AREA("C", "文本域");
    @JsonValue
    private final String sign;
    private final String remake;

    VoteOptionType(String sign, String remake) {
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
