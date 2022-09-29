package tyut.selab.schedule.enums;

public enum Status {
    ENABLE(0, "有效"),
    DELETED(1, "被删除"),
    EXPIRED(2, "过期的");

    private final int id;
    private final String comment;

    Status(int id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }
}
