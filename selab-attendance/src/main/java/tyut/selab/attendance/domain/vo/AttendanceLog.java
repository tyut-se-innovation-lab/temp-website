package tyut.selab.attendance.domain.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用于给前端返回本周的日志信息
 */
@Data
public class AttendanceLog {
    /**
     * 用户名
     */
    String userName;

    /**
     * 签到签退时间
     * 这里的数组长度为2
     * 签到时间-签退时间
     */
    List<Date[]> signTimes;

    /**
     * 今日签到总时长
     */
    int signTime;
}
