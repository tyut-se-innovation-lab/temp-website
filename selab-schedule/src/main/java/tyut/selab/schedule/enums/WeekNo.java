package tyut.selab.schedule.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum WeekNo {
    FIRST_TERM(1, "第一周"),
    SECOND_TERM(2, "第二周"),
    THIRD_TERM(3, "第三周"),
    FORTH_TERM(4, "第四周"),
    FIFTH_TERM(5, "第五周"),
    SIXTH_TERM(6, "第六周"),
    SEVENTH_TERM(7, "第七周"),
    EIGHTH_TERM(8, "第八周"),
    NINTH_TERM(9, "第九周"),
    TENTH_TERM(10, "第十周"),
    ELEVENTH_TERM(11, "第十一周"),
    TWELFTH_TERM(12, "第十二周"),
    THIRTEENTH_TERM(13, "第十三周"),
    FOURTEENTH_TERM(14, "第十四周"),
    FIFTEENTH_TERM(15, "第十五周"),
    SIXTEENTH_TERM(16, "第十六周"),
    SEVENTEENTH_TERM(17, "第十七周"),
    EIGHTEENTH_TERM(18, "第十八周"),
    NINETEENTH_TERM(19, "第十九周"),
    TWENTIETH_TERM(20, "第二十周"),
    FIRST_EXTRA_TERM(21, "小学期第一周"),
    SECOND_EXTRA_TERM(22, "小学期第二周");

    private final int id;
    private final String name;

    WeekNo(int id, String name) {
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
