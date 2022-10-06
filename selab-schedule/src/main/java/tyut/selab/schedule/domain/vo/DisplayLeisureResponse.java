package tyut.selab.schedule.domain.vo;

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
    private Long roleId;
    /**
     * 性别
     */
    private String sex;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 部门名称
     */
    private String deptName;

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

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "DisplayLeisureResponse{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", deptId=" + deptId +
                ", roleId=" + roleId +
                ", sex='" + sex + '\'' +
                ", roleName='" + roleName + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
