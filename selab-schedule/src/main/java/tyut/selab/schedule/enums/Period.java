package tyut.selab.schedule.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Period {
    FIRST(1, "第一节课"),
    SECOND(2, "第二节课"),
    THIRD(3, "第三节课"),
    FORTH(4, "第四节课"),
    FIFTH(5, "第五节课"),
    SIXTH(6, "第六节课"),
    SEVENTH(7, "第七节课"),
    EIGHTH(8, "第八节课"),
    NINTH(9, "第九节课"),
    TENTH(10, "第十节课");

    @JsonValue
    private final int id;
    private final String name;

    Period(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Period getPeriodById(int id){
        return values()[id -1];
    }
}
