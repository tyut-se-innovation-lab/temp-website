package tyut.selab.schedule.domain.vo;

import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;

public class UploadScheduleRequest {
    /**
     * 第几节课
     */
    private int period;
    /**
     * 星期几
     */
    private int week;
    /**
     * 第几周
     */
    private int weekNo;
    /**
     * 课程名称
     */
    private String courseTitle;

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

    @Override
    public String toString() {
        return "UploadScheduleRequest{" +
                "period=" + period +
                ", week=" + week +
                ", weekNo=" + weekNo +
                ", courseTitle='" + courseTitle + '\'' +
                '}';
    }
}
