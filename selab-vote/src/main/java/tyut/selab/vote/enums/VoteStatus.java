package tyut.selab.vote.enums;

public enum VoteStatus {
    UNDERWAY(0, "进行中"),
    FINISH(1, "结束"),
    WITHDRAW(2, "被撤回");
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
