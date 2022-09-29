package tyut.selab.vote.enums;

public enum VoteOptionType {
    CONTAINER("C", "容器"),
    QUESTION("Q", "问题"),
    SINGLE_CHECKBOX("S", "单选框"),
    MULTIPLE_CHECKBOX("M", "多选框"),
    TEXT_AREA("T", "文本域");
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
