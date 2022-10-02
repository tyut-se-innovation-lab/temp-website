package tyut.selab.schedule.enums;

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

    private final int id;
    private final String name;

    Period(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Period getPeriodById(int id){
        switch (id){
            case 1:
                return FIRST;
            case 2:
                return SECOND;
            case 3:
                return THIRD;
            case 4:
                return FORTH;
            case 5:
                return FIFTH;
            case 6:
                return SIXTH;
            case 7:
                return SEVENTH;
            case 8:
                return EIGHTH;
            case 9:
                return NINTH;
            case 10:
                return TENTH;
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
