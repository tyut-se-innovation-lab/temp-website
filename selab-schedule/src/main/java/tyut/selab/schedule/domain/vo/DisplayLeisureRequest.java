package tyut.selab.schedule.domain.vo;

import tyut.selab.schedule.domain.TimeFrame;

import java.util.List;

public class DisplayLeisureRequest {
    /**
     * 需要展示空课的时间段
     */
    private List<TimeFrame> timeFrames;
    /**
     * 需要展示空课的部门ID
     */
    private List<Long> deptIds;
    /**
     * 需要展示空课的角色ID
     */
    private List<Long> roleIds;

    public List<TimeFrame> getTimeFrames() {
        return timeFrames;
    }

    public void setTimeFrames(List<TimeFrame> timeFrames) {
        this.timeFrames = timeFrames;
    }

    public List<Long> getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(List<Long> deptIds) {
        this.deptIds = deptIds;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
