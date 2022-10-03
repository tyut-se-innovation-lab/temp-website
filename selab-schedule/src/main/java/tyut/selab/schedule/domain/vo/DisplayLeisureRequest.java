package tyut.selab.schedule.domain.vo;

import tyut.selab.schedule.domain.TimeFrame;

import java.util.List;

public class DisplayLeisureRequest {
    private List<TimeFrame> timeFrames;
    private List<Long> DeptIds;
    private List<Long> roleIds;

    public List<TimeFrame> getTimeFrames() {
        return timeFrames;
    }

    public void setTimeFrames(List<TimeFrame> timeFrames) {
        this.timeFrames = timeFrames;
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
