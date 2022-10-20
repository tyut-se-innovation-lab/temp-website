package tyut.selab.vote.enums;

public enum VoteType {
    ASSUME_BOSS(1, "控诉管理人员", ""),
    SEEK_OPTIONS(2, "征求意见",""),
    VOTE(3,"投票",""),
    CUSTOM(100,"自定义类型","");

    private final int id;
    private final String name;
    private final String remark;

    VoteType(int id, String name, String remark) {
        this.id = id;
        this.name = name;
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }
}