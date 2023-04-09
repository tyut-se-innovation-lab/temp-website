package tyut.selab.attendance.domain.po;


import com.alibaba.fastjson2.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import tyut.selab.attendance.domain.vo.AttendanceLog;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2023/3/24 15:00
 */
@Data
public class Attendance {

    /**
     * 数据唯一标识
     */
    int id;

    /**
     * 用户名
     */
    String userName;

    /**
     * 签到时间
     */
    @JsonFormat(pattern = "yyyy-MM--dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(format = "yyyy-MM--dd HH:mm:ss")
    Date attStartTime;

    /**
     * 签退时间
     */
    @JsonFormat(pattern = "yyyy-MM--dd HH:mm:ss",timezone = "GMT+8")
    @JSONField(format = "yyyy-MM--dd HH:mm:ss")
    Date attEndTime;

    /**
     * 今日签到总时长
     */
    String signTime;

    /**
     * Attendance类向AttendanceLog类转换
     * @return
     */
    public AttendanceLog change(){
        AttendanceLog attendanceLog = new AttendanceLog();
        attendanceLog.setUserName(this.getUserName());
        attendanceLog.setSignTimes(Arrays.asList(this.getAttStartTime(),this.getAttEndTime()));
        attendanceLog.setSignTime(this.getSignTime());
        return attendanceLog;
    }
}
