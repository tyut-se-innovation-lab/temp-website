package tyut.selab.attendance.service.Impl;

import com.ruoyi.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.domain.po.CouldSignOut;
import tyut.selab.attendance.mapper.AttendanceMapper;
import tyut.selab.attendance.service.IAttendanceService;

import java.util.Calendar;
import java.util.Date;

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
    public CouldSignOut couleSignOut() {
        //名字、签到时间、签退时间、总时间
        Attendance attendance = attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName());
        if (attendance != null && attendance.getAttStartTime() != null && attendance.getAttEndTime() == null){
            if(attendance.getAttStartTime().getDate() != new Date().getDate()){
                return new CouldSignOut(false);
            }
            return new CouldSignOut(attendance.getAttStartTime(),true);
        }
        return new CouldSignOut(false);
    }

    @Override
    public Boolean signOut() {
        Date attEndTime = dateTime(YYYY_MM_DD_HH_MM_SS,getTime());
        Attendance attendance = attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName());
        Date attStartTime = attendance.getAttStartTime();
        CouldSignOut couldSignOut = couleSignOut();
        if (couldSignOut.getCouldSignOut()){
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(attStartTime);
            cal2.setTime(attEndTime);
            int minute = differentHoursByMillisecond(attEndTime, attStartTime);
            String interval = String.format("%4.2f",minute/60.0);
            return attendanceMapper.signOut(sysUserMapper.selectUserById(getUserId()).getNickName(), attEndTime,interval) > 0;
        }else return false;
    }
}
