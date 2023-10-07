package tyut.selab.attendance.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import tyut.selab.attendance.domain.po.Attendance;

import java.util.Date;
import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2023/3/26 19:51
 */
@Mapper
public interface AttendanceLogMapper {

    /**
     * 查询本周日志
     * @return
     */
    public List<Attendance> couleSignOut(@Param("startTime") Date startTime,@Param("endTime") Date endTime);

    /**
     * 统计本周日志
     */
    public void statisticsLogs();

    /**
     * 根据部门id获得部员id
     * @param deptId
     * @return
     */
    public List<Integer> deptUserId(@Param("deptId") int deptId);

    /**
     * 根据部门id获得部员日志
     * @return
     */
    public List<Attendance> userTime(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("roleId") int roleId);

    public List allUserId();

}