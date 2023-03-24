package tyut.selab.attendance.service.Impl;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.domain.vo.AttendanceLog;
import tyut.selab.attendance.mapper.AttendanceMapper;
import tyut.selab.attendance.service.IAttendanceService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.ruoyi.common.utils.DateUtils.*;
import static com.ruoyi.common.utils.SecurityUtils.getUserId;
import static com.ruoyi.common.utils.SecurityUtils.getUsername;

/**
 * @Author: Gulu
 * @Date: 2023/3/24 9:57
 */
@Service
public class AttendanceServiceImpl implements IAttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;


    @Override
    public Boolean signIn() {
        Date attStartTime = dateTime(YYYY_MM_DD_HH_MM_SS,getTime());
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(attendanceMapper.couleSignOut(getUsername()).getAttStartTime());
        cal2.setTime(attStartTime);
        int date1 = cal1.get(Calendar.DATE);
        int date2 = cal2.get(Calendar.DATE);
        if (date1 == date2){
            attendanceMapper.signIn(getUsername(),attStartTime);
            return true;
        }else return false;
    }

    @Override
    public Boolean couleSignOut() {
        Date attEndTime = dateTime(YYYY_MM_DD_HH_MM_SS,getTime());
        Attendance attendance = attendanceMapper.couleSignOut(getUsername());
        int hoursByMillisecond = differentHoursByMillisecond(attEndTime, attendance.getAttStartTime());
        return hoursByMillisecond >= 1;
    }

    @Override
    public Boolean signOut() {
        Date attEndTime = dateTime(YYYY_MM_DD_HH_MM_SS,getTime());
        int interval = differentHoursByMillisecond(attEndTime, attendanceMapper.selectAttendance(getUsername()));
        return attendanceMapper.signOut(getUsername(), attEndTime,interval) > 0;
    }
}
