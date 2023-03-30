package tyut.selab.attendance.service;

import java.util.Date;

/**
 * 今日签到相关功能
 */
public interface IAttendanceService {
    /**
     * 签到
     * @return
     */
    Boolean signIn();

    /**
     * 是否允许签退
     * @return
     */
    Date couleSignOut();

    /**
     * 签退
     * @return
     */
    Boolean signOut();

}
