package tyut.selab.schedule.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Status {
    ENABLE(0, "有效"),
    DELETED(1, "被删除"),
    EXPIRED(2, "过期的");

    @JsonValue
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
