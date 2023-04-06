package tyut.selab.attendance.service.Impl;

import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.domain.vo.AttendanceLog;
import tyut.selab.attendance.mapper.AttendanceLogMapper;
import tyut.selab.attendance.service.IAttendanceLogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    public List<AttendanceLog> couleSignOut(int pageNum) {
        List<Attendance> attendances = attendanceLogMapper.couleSignOut((pageNum - 1) * 15);
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
        File folder = new File("../signlog/");
        try {
            if(!folder.exists()){
                folder.mkdirs();
                System.err.println("已创建文件夹"+folder);
            } else {
                System.out.println(folder.getPath());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        List<String> fileLists = new ArrayList<>();
        File[] files = folder.listFiles();
        if (files != null){
            for (File file : files) {
                fileLists.add(file.getName());
            }
        }
        return fileLists;
    }

    @Override
    public void getFileByName(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) throws IOException {
        File file = new File(filePath + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
        }
}
