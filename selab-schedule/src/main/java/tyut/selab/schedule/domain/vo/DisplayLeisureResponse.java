package tyut.selab.schedule.domain.vo;

import java.util.List;

public class DisplayLeisureResponse {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 角色ID
     */
    private List<Long> roleIds;
    /**
     * 性别
     */
    private String sex;
    /**
     * 角色名称
     */
    private List<String> roleNames;
    /**
     * 部门名称
     */
    private String deptName;

    private String nickName;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    @Override
    public String toString() {
        return "DisplayLeisureResponse{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", deptId=" + deptId +
                ", roleIds=" + roleIds +
                ", sex='" + sex + '\'' +
                ", roleNames=" + roleNames +
                ", deptName='" + deptName + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

}
