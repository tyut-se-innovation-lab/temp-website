package tyut.selab.schedule.domain.vo;

import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;

public class UploadScheduleRequest {
    /**
     * 第几节课
     */
    private Period period;
    /**
     * 星期几
     */
    private Week week;
    /**
     * 第几周
     */
    private WeekNo weekNo;
    /**
     * 课程名称
     */
    private String courseTitle;

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Week getWeek() {
        return week;
    }

    public void setWeek(Week week) {
        this.week = week;
    }

    public WeekNo getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(WeekNo weekNo) {
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
