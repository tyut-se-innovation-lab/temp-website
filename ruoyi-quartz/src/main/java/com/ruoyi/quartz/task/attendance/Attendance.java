package com.ruoyi.quartz.task.attendance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.attendance.mapper.GenerateLogMapper;
import tyut.selab.attendance.service.Impl.GenerateLogServiceImpl;

@Component("attendance")
public class Attendance {
    @Autowired
    GenerateLogMapper generateLogMapper;
    @Autowired
    GenerateLogServiceImpl generateLogService;

    public void clearAttendanceLogInDB(){
        generateLogMapper.deleteInvalidData();
    }
    public void createAttendanceLogFile(){
        generateLogService.writeLogFileThisWeek(); //生成本周签到日志文件
        generateLogService.deleteThisWeekLog(); //删除本周签到日志记录
    }
}
