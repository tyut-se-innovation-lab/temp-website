package tyut.selab.attendance.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.attendance.service.Impl.AttendanceLogServiceImpl;

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
    public AjaxResult getThisWeekLog(){
        return AjaxResult.success(attendanceLogService.couleSignOut());
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
     * @return 某文件
     */
    @PreAuthorize("@ss.hasPermi('attendance:log')")
    @PostMapping("/file")
    @ResponseBody
    public AjaxResult signIn(){
        return AjaxResult.success();
    }



}
