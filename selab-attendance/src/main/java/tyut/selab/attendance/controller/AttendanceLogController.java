package tyut.selab.attendance.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.attendance.service.Impl.AttendanceLogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.apache.commons.lang3.SystemUtils.getUserName;

@RestController
@RequestMapping("/selab/attendance/log")
public class AttendanceLogController {

    @Autowired
    AttendanceLogServiceImpl attendanceLogService;

    /**
     * 查看本周日志
     * @return 本周日志信息
     */
    @PreAuthorize("@ss.hasPermi('attendance:log')")
    @GetMapping("/week")
    @ResponseBody
    public AjaxResult getThisWeekLog(int pageNum){
        return AjaxResult.success(attendanceLogService.couleSignOut(pageNum));
    }

    /**
     * 获取导出的文件列表
     * @return 文件列表
     */
    @PreAuthorize("@ss.hasPermi('attendance:log')")
    @GetMapping("/log")
    @ResponseBody
    public AjaxResult getLogList(){
        return AjaxResult.success(attendanceLogService.getLogFileList());
    }

    /**
     * 根据文件名，下载某文件
     */
    @PreAuthorize("@ss.hasPermi('attendance:log')")
    @GetMapping("/{filename}")
    @ResponseBody
    public void getFileByName(HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename) throws IOException {
        String filePath = "../signlog/"; // 您的文件路径
        attendanceLogService.getFileByName(request, response, filePath, filename);
    }
}
