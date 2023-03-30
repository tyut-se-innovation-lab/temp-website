package tyut.selab.attendance.service.Impl;

import com.ruoyi.system.mapper.SysUserMapper;
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
/**
 * @Author: Gulu
 * @Date: 2023/3/24 9:57
 */
@Service
public class AttendanceServiceImpl implements IAttendanceService {
    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    SysUserMapper sysUserMapper;


    @Override
    public Boolean signIn() {
        Date attStartTime = dateTime(YYYY_MM_DD_HH_MM_SS,getTime());
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        Attendance attendance = attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName());
        if(attendance == null){
            attendanceMapper.signIn(sysUserMapper.selectUserById(getUserId()).getNickName(),attStartTime);
            return true;
        }else{
            if (attendance.getAttEndTime() == null){
                cal1.setTime(attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName()).getAttStartTime());
                cal2.setTime(attStartTime);
                int date1 = cal1.get(Calendar.DATE);
                int date2 = cal2.get(Calendar.DATE);
                if (date1 != date2){
                    attendanceMapper.signIn(sysUserMapper.selectUserById(getUserId()).getNickName(),attStartTime);
                    return true;
                }else return false;
            }else {
                attendanceMapper.signIn(sysUserMapper.selectUserById(getUserId()).getNickName(),attStartTime);
                return true;
            }
        }
    }

    @Override
    public Date couleSignOut() {
        Attendance attendance = attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName());
        if (attendance.getAttEndTime() == null){
            return attendance.getAttStartTime();
        }else return null;
    }

    @Override
    public Boolean signOut() {
        Date attEndTime = dateTime(YYYY_MM_DD_HH_MM_SS,getTime());
        Date attStartTime = attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName()).getAttStartTime();
        if (attStartTime != null){
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(attStartTime);
            cal2.setTime(attEndTime);
            int date1 = cal1.get(Calendar.DATE);
            int date2 = cal2.get(Calendar.DATE);
            if (date1 == date2){
                int minute = differentHoursByMillisecond(attEndTime, attStartTime);
                String interval = String.format("%4.2f",minute/60.0);
                if (minute >= 60){
                    return attendanceMapper.signOut(sysUserMapper.selectUserById(getUserId()).getNickName(), attEndTime,interval) > 0;
                }else return false;
            }return false;
        }return false;
    }
}
