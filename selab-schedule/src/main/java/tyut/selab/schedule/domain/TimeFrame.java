package tyut.selab.schedule.domain;

import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;

public class TimeFrame {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeFrame timeFrame = (TimeFrame) o;

        if (period != timeFrame.period) return false;
        if (week != timeFrame.week) return false;
        return weekNo == timeFrame.weekNo;
    }

    @Override
    public int hashCode() {
        int result = period != null ? period.hashCode() : 0;
        result = 31 * result + (week != null ? week.hashCode() : 0);
        result = 31 * result + (weekNo != null ? weekNo.hashCode() : 0);
        return result;
    }
}