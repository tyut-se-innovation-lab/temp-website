package com.ruoyi.quartz.task.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.attendance.mapper.GenerateLogMapper;
import tyut.selab.attendance.service.Impl.AttendanceLogServiceImpl;
import tyut.selab.attendance.service.Impl.GenerateLogServiceImpl;

import javax.annotation.Resource;

@Component("attendance")
public class Attendance {

    @Resource
    GenerateLogServiceImpl generateLogService;

    @Resource
    GenerateLogMapper generateLogMapper;

    @Resource
    AttendanceLogServiceImpl attendanceLogService;


    public void clearAttendanceLogInDB(){
        generateLogService.deleteInvalidData();//删除每日未签退数据
        generateLogMapper.wasteRemoval(); //删除非大一的废物们的数据
    }
    public void createAttendanceLogFile(){
        attendanceLogService.statisticsLogs(); //对本周签到数据进行统计
        generateLogService.writeLogFileThisWeek(); //生成本周签到日志文件
        generateLogService.deleteThisWeekLog(); //删除本周签到日志记录
        generateLogMapper.deleteThisWeekLogStatistics(); //删除本周统计日志记录
    }

    public void createFile(){
        attendanceLogService.statisticsLogs(); //对本周签到数据进行统计
        generateLogService.writeLogFileThisWeek(); //生成本周签到日志文件
    }
}
