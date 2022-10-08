package tyut.selab.schedule.domain.vo;

/**
 * @author Big_bai on 2022/10/7
 */
public class ScheduleDisplayResponse {
    /**
     * 第几节课
     */
    int period;
    /**
     * 星期几
     */
    int week;
    /**
     * 第几周
     */
    int weekNo;
    /**
     * 课程名称
     */
    String courseTitle;

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
