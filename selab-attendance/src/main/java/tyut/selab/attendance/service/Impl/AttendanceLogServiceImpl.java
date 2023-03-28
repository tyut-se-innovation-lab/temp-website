package tyut.selab.attendance.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.domain.vo.AttendanceLog;
import tyut.selab.attendance.mapper.AttendanceLogMapper;
import tyut.selab.attendance.service.IAttendanceLogService;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2023/3/26 19:50
 */
@Service
public class AttendanceLogServiceImpl implements IAttendanceLogService {

    @Autowired
    AttendanceLogMapper attendanceLogMapper;

    @Override
    public void statisticsLogs() {
        attendanceLogMapper.statisticsLogs();
    }

    @Override
    public List<AttendanceLog> couleSignOut() {
        List<Attendance> attendances = attendanceLogMapper.couleSignOut();
        List<AttendanceLog> attendanceLogs = new ArrayList<>();
        if (attendances != null){
            for (Attendance attendance:attendances) {
                attendanceLogs.add(attendance.change());
            }
        }
        return attendanceLogs;
    }

    @Override
    public List<String> getLogFileList() {
        File folder = new File("../selab-attendance/src/main/resources/signlog/");
        List<String> fileLists = new ArrayList<>();
        String[] fileNames = folder.list();
        if (fileNames != null){
            fileLists.addAll(Arrays.asList(fileNames));
        }
        return fileLists;
    }

    @Override
    public File getFileByName(String fileName) {
        return null;
    }
}
