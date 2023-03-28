package tyut.selab.attendance.service;

import tyut.selab.attendance.domain.vo.AttendanceLog;

import java.io.File;
import java.util.List;

/**
 * 查看记录相关
 */
public interface IAttendanceLogService {

    /**
     * 统计本周日志
     */
    void statisticsLogs();

    /**
     * 查看本周日志
     * @return 日志信息
     */
    List<AttendanceLog> couleSignOut();

    /**
     * 获取日志文件列表
     * @return 日志文件列表
     */
    List<String> getLogFileList();

    /**
     * 根据名字获取某文件
     * @param fileName 文件名字
     * @return 某个文件
     */
    File getFileByName(String fileName);
}
