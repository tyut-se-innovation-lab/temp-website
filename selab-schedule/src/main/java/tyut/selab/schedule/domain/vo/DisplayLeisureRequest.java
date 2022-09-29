package tyut.selab.schedule.domain.vo;

import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;

import java.util.List;

public class DisplayLeisureRequest {
    private List<Period> period;
    private List<Week> week;
    private List<WeekNo> weekNo;
    private List<Long> DeptIds;
    private List<Long> roleIds;

    public List<Period> getPeriod() {
        return period;
    }

    public void setPeriod(List<Period> period) {
        this.period = period;
    }

    public List<Week> getWeek() {
        return week;
    }

    public void setWeek(List<Week> week) {
        this.week = week;
    }

    public List<WeekNo> getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(List<WeekNo> weekNo) {
        this.weekNo = weekNo;
    }

    public List<Long> getDeptIds() {
        return DeptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        DeptIds = deptIds;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
