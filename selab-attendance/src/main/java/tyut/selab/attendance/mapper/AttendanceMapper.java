package tyut.selab.attendance.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.domain.vo.AttendanceLog;

import java.util.Date;

/**
 * @Author: Gulu
 * @Date: 2023/3/24 10:10
 */
public interface AttendanceMapper {
    /**
     * 参与签到
     * @param userName 签到发起人
     * @param attStartTime 签到时间
     * @return
     */
    public int signIn(@Param("userName")String userName, @Param("attStartTime")Date attStartTime);

    /**
     * 查询是否允许签退
     * @param userName 发起签退人员
     * @return
     */
    public Attendance couleSignOut(String  userName);

    /**
     * 签退
     * @param userName 发起签退人员
     * @param attEndTime 签退时间
     * @return
     */
    public int signOut(@Param("userName")String  userName,@Param("attEndTime")Date attEndTime,@Param("interval")String interval);
}
