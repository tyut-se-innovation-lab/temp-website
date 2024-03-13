package tyut.selab.attendance.domain.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: Gulu
 * @Date: 2023/3/30 21:28
 */
@Data
public class CouldSignOut {

    /**
     * 签到时间
     */
    Date attStartTime;

    /**
     * 是否可以签退
     */
    Boolean couldSignOut;

    public CouldSignOut(Boolean couldSignOut) {
        this.couldSignOut = couldSignOut;
    }

    public CouldSignOut() {
    }

    public CouldSignOut(Date attStartTime, Boolean couldSignOut) {
        this.attStartTime = attStartTime;
        this.couldSignOut = couldSignOut;
    }
}
