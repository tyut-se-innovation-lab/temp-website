package tyut.selab.attendance.domain.po;

import com.github.pagehelper.PageInfo;
import lombok.Data;

@Data
public class AttendanceLogPo {
    int total;
    PageInfo<Attendance> attendancePageInfo;

    public AttendanceLogPo(int total, PageInfo<Attendance> attendancePageInfo) {
        this.total = total;
        this.attendancePageInfo = attendancePageInfo;
    }
}
