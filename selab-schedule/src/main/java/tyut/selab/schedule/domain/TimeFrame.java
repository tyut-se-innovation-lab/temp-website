package tyut.selab.schedule.domain;

import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;

public class TimeFrame {
    private Period period;
    private Week week;
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
}
