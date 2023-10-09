package tyut.selab.attendance.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginUser;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.mapper.AttendanceLogMapper;
import tyut.selab.attendance.service.IAttendanceLogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Author: Gulu
 * @Date: 2023/3/26 19:50
 */
@Service
public class AttendanceLogServiceImpl implements IAttendanceLogService {

    private LoginUser loginUser;
    @Autowired
    AttendanceLogMapper attendanceLogMapper;

    @Override
    public void statisticsLogs() {
        attendanceLogMapper.statisticsLogs();
    }

    @Override
    public PageInfo<Attendance> bookPageInfo(@Nullable Long attStartTime,@Nullable Long attEndTime, int pageNum, int pageSize) {
        Date start = new Date();
        Date end = new Date();
        if (attStartTime != null && attEndTime != null){
            start.setTime(attStartTime);
            end.setTime(attEndTime);
        }else {
            start = null;
            end = null;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Attendance> attendances;
        if (start != null){
                attendances = attendanceLogMapper.couleSignOut(start,end);
        }else attendances = attendanceLogMapper.couleSignOut(null,null);
        return new PageInfo<>(attendances);
    }
    public PageInfo<Attendance> totalBookPageInfo(@Nullable Long attStartTime,@Nullable Long attEndTime, int pageNum, int pageSize) {
        Date start = new Date();
        Date end = new Date();
        if (attStartTime != null && attEndTime != null){
            start.setTime(attStartTime);
            end.setTime(attEndTime);
        }else {
            start = null;
            end = null;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<Integer> allUserIds=attendanceLogMapper.allUserId();
        List<Attendance> totalAttendances = null;
        if (start != null){
            for (int allUserId : allUserIds) {
                List<Attendance> attendances = attendanceLogMapper.userTime(start, end, allUserId);
                double total = 0;
                for (Attendance attendance : attendances) {
                    total = total + Double.parseDouble(attendance.getSignTime());
                }
                Attendance totalAttendance = attendances.get(0);
                totalAttendance.setSignTime(String.valueOf(total));
                totalAttendances.add(totalAttendance);
            }

        }else totalAttendances = attendanceLogMapper.couleSignOut(null,null);
        return new PageInfo<>(totalAttendances);
    }

    @Override

    public PageInfo<Attendance> departmentBookPageInfo(Long attStartTime, Long attEndTime, int pageNum, int pageSize, int deptId) {
        Date start = new Date();
        Date end = new Date();
        List<Attendance> totalAttendances = new ArrayList<>();
        if (attStartTime != null && attEndTime != null) {
            start.setTime(attStartTime);
            end.setTime(attEndTime);
        } else {
            start = null;
            end = null;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<String> userNames = attendanceLogMapper.nickName(deptId);
        if (start != null) {
            for (String userName: userNames) {
                List<Attendance> attendances = attendanceLogMapper.userTimeName(start, end, userName);
                if (attendances.size() !=0){
                double total = 0;
                for (Attendance attendance : attendances) {
                    String signTimeStr = attendance.getSignTime();
                    if (signTimeStr != null) {
                        try {
                            double d = Double.parseDouble(signTimeStr);
                            System.out.println("转换后的double值为: " + d);
                            total = total + d;
                        } catch (NumberFormatException e) {
                            System.err.println("无法将字符串转换为double: " + signTimeStr);
                        }
                    }
                }
                Attendance totalAttendance = attendances.get(0);
                totalAttendance.setSignTime(String.valueOf(total));
                    if (!attendances.isEmpty()) {
                        totalAttendances.add(totalAttendance);
                    }

                }
                else continue;
            }
        }
        else {
            for (String userName : userNames) {
                List<Attendance> attendances = attendanceLogMapper.userTimeName(null, null, userName);
                if (attendances.size() != 0) {
                    double total = 0;
                    for (Attendance attendance : attendances) {
                        String signTimeStr = attendance.getSignTime();
                        if (signTimeStr != null) {
                            try {
                                double d = Double.parseDouble(signTimeStr);
                                System.out.println("转换后的double值为: " + d);
                                total = total + d;
                            } catch (NumberFormatException e) {
                                System.err.println("无法将字符串转换为double: " + signTimeStr);
                            }
                        }
                    }
                    Attendance totalAttendance = attendances.get(0);
                    totalAttendance.setSignTime(String.valueOf(total));
                    if (!attendances.isEmpty()) {
                        totalAttendances.add(totalAttendance);
                    }

                } else continue;

            }
        }
        return new PageInfo<>(totalAttendances);
    }
    public double userWeekTime() {
        Long userId =loginUser.getUserId();
        String userName = attendanceLogMapper.nickUserName(userId);
        List<Attendance> attendances = attendanceLogMapper.userTimeName(null, null,userName );
        double total = 0;
        if (attendances.size() != 0) {
            for (Attendance attendance : attendances) {
                String signTimeStr = attendance.getSignTime();
                if (signTimeStr != null) {
                    try {
                        double d = Double.parseDouble(signTimeStr);
                        System.out.println("转换后的double值为: " + d);
                        total = total + d;
                    } catch (NumberFormatException e) {
                        System.err.println("无法将字符串转换为double: " + signTimeStr);
                        return 0;
                    }
                }
            }
        }else  total = 0;

        return total;
    }
    @Override
    public List<String> getLogFileList() {
        File folder = new File("/var/temp_website/signlog");
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
