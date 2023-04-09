package tyut.selab.attendance.service;

import com.github.pagehelper.PageInfo;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PathVariable;
import tyut.selab.attendance.domain.po.Attendance;
import tyut.selab.attendance.domain.vo.AttendanceLog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
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
     * 查看本周日志分页查询
     * @param pageNum 当前页数
     * @param pageSize 每页的数据行数
     * @return
     */
    PageInfo<Attendance> bookPageInfo(@Nullable Long attStartTime, @Nullable Long attEndTime, int pageNum, int pageSize);

    /**
     * 获取日志文件列表
     * @return 日志文件列表
     */
    List<String> getLogFileList();

    /**
     *
     * @param request 请求对象
     * @param response 响应对象
     * @param filePath 文件路径
     * @param fileName 文件名
     * @throws IOException
     */
    void getFileByName(HttpServletRequest request, HttpServletResponse response, String filePath, String fileName) throws IOException;
}
