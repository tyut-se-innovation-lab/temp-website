package tyut.selab.schedule.domain.vo;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.SysUserRole;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;

import java.util.List;

/**
 * @author 冯洋
 * @version 1.0
 */
public class ArrangePeriodResponse {
    private Long userId;
    private String userName;
    private Period period;
    private Week week;
    private WeekNo weekNo;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

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
