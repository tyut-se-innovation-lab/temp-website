package tyut.selab.attendance.service;

import java.util.Date;

/**
 * 今日签到相关功能
 */
public interface IAttendanceService {
    /**
     * 签到
     * @param userId 用户id
     * @return 当前签到时间
     */
    Date signIn(Long userId);

    /**
     * 是否允许签退
     * @param userId 用户id
     * @return 是否允许
     */
    Boolean couleSignOut(Long userId);

    /**
     * 签退
     * @param userId 用户id
     * @return 签退时间
     */
    Boolean signOut(Long userId);

}
