package tyut.selab.schedule.enums;

public enum Week {
    MONDAY(1, "星期一"),
    TUESDAY(2, "星期二"),
    WEDNESDAY(3, "星期三"),
    THURSDAY(4, "星期四"),
    FRIDAY(5, "星期五"),
    SATURDAY(6, "星期六"),
    SUNDAY(7, "星期日");

    private final int id;
    private final String name;

    Week(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Week getWeekById(int id){
        switch (id){
            case 1:
                return MONDAY;
            case 2:
                return TUESDAY;
            case 3:
                return WEDNESDAY;
            case 4:
                return THURSDAY;
            case 5:
                return FRIDAY;
            case 6:
                return SATURDAY;
            case 7:
                return SUNDAY;
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
