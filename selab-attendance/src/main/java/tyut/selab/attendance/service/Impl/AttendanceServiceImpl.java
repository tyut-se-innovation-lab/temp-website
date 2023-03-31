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
        CouldSignOut couldSignOut = new CouldSignOut();
        Date attEndTime = dateTime(YYYY_MM_DD_HH_MM_SS,getTime());
        Attendance attendance = new Attendance();
        if (attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName()) != null){
            attendance = attendanceMapper.couleSignOut(sysUserMapper.selectUserById(getUserId()).getNickName());
        }
        if(attendance.getAttStartTime() != null){ //当前库中有此人签到时间记录
            Date attStartTime = attendance.getAttStartTime();
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(attStartTime);
            cal2.setTime(attEndTime);
            int date1 = cal1.get(Calendar.DATE);
            int date2 = cal2.get(Calendar.DATE);
            couldSignOut.setCouldSignOut(false);
            couldSignOut.setAttStartTime(null);
            if (date1 == date2){ //当前时间和签到时间是同一天
                couldSignOut.setAttStartTime(attStartTime);
                int minute = differentHoursByMillisecond(attEndTime, attStartTime);
                int hour = cal2.get(Calendar.HOUR_OF_DAY); //签退时间小时数
                int minutes = cal2.get(Calendar.MINUTE); //签退时间分钟数
                if (hour <= 18 || (hour == 19 && minutes < 30)){ //晚上7点半到9点半不允许签退
                        if (minute >= 60){
                            couldSignOut.setCouldSignOut(true);
                        }
                } else if (hour >= 22 || (hour == 21 && minutes > 30)) {
                    if (minute >= 60){
                        couldSignOut.setCouldSignOut(true);
                    }
                }
            }
            if (attendance.getAttEndTime() != null){ //有此人签到和签退记录,不能签退，请先签到
                couldSignOut.setCouldSignOut(false);
            }
        }else { //当前库中无此人签到记录,不允许签退
            couldSignOut.setCouldSignOut(false);
            couldSignOut.setAttStartTime(null);
        }
        return couldSignOut;
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
