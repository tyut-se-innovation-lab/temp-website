package tyut.selab.vote.enums;

public enum VoteOption {
    CONTAINER("C", "容器"),
    QUESTION("Q", "问题"),
    SINGLE_CHECKBOX("S", "单选框"),
    MULTIPLE_CHECKBOX("M", "多选框"),
    TEXT_AREA("T", "文本域");
    private String sign;
    private String remake;

    VoteOption(String sign, String remake) {
        this.sign = sign;
        this.remake = remake;
    }
}
