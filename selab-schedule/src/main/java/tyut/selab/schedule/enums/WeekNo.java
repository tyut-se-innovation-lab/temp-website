package tyut.selab.schedule.enums;

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

    public static WeekNo getWeekNoById(int id){
        switch (id){
            case 1:
                return FIRST_TERM;
            case 2:
                return SECOND_TERM;
            case 3:
                return THIRD_TERM;
            case 4:
                return FORTH_TERM;
            case 5:
                return FIFTH_TERM;
            case 6:
                return SIXTH_TERM;
            case 7:
                return SEVENTH_TERM;
            case 8:
                return EIGHTH_TERM;
            case 9:
                return NINTH_TERM;
            case 10:
                return TENTH_TERM;
            case 11:
                return ELEVENTH_TERM;
            case 12:
                return TWELFTH_TERM;
            case 13:
                return THIRTEENTH_TERM;
            case 14:
                return FOURTEENTH_TERM;
            case 15:
                return FIFTEENTH_TERM;
            case 16:
                return SIXTEENTH_TERM;
            case 17:
                return SEVENTEENTH_TERM;
            case 18:
                return EIGHTEENTH_TERM;
            case 19:
                return NINETEENTH_TERM;
            case 20:
                return TWENTIETH_TERM;
            case 21:
                return FIRST_EXTRA_TERM;
            case 22:
                return SECOND_EXTRA_TERM;
            default:
                return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
