package tyut.selab.schedule.domain.vo;

import tyut.selab.schedule.enums.WeekNo;

public class ArrangePeriodRequest {
    /**
     * 距15h还剩多少h
     */
    private Integer needTime;
    /**
     * 空闲的座位数
     */
    private Integer seatNum;
    /**
     * 需要排第几节课
     */
    private WeekNo weekNo;
    /**
     * 给什么角色排课
     */
    private Long roleId;


    public WeekNo getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(WeekNo weekNo) {
        this.weekNo = weekNo;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Integer getNeedTime() {
        return needTime;
    }

    public void setNeedTime(Integer needTime) {
        this.needTime = needTime;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }
}
