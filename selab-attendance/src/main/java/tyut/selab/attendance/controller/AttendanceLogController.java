package tyut.selab.attendance.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.attendance.service.Impl.AttendanceLogServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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
    public AjaxResult getThisWeekLog(@Nullable @RequestParam(required = false,value = "attStartTime") Long attStartTime,
                                     @Nullable @RequestParam(required = false,value = "attEndTime")

                                     Long attEndTime,
                                     @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "15") int pageSize){
        return AjaxResult.success(attendanceLogService.bookPageInfo(attStartTime,attEndTime,pageNum,pageSize));
    }
    /**
     * 查看alluser日志+时间总和
     * @return
     */
    @PreAuthorize("@ss.hasPermi('attendance:log')")
    @GetMapping("/allTime")
    @ResponseBody
    public AjaxResult allUserTime(@Nullable @RequestParam(required = false,value = "attStartTime") Long attStartTime,
                                     @Nullable @RequestParam(required = false,value = "attEndTime")

                                     Long attEndTime,
                                     @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "15") int pageSize){
        return AjaxResult.success(attendanceLogService.totalBookPageInfo(attStartTime,attEndTime,pageNum,pageSize));
    }
    /**
     * 查看部门日志+时间总和
     * @return 部门日志信息
     */
    @PreAuthorize("@ss.hasPermi('attendance:log')")
    @GetMapping("/deptTime")
    @ResponseBody
    public AjaxResult getDeptDataTimeLog(@Nullable @RequestParam(required = false,value = "attStartTime") Long attStartTime,
                                     @Nullable @RequestParam(required = false,value = "attEndTime") Long attEndTime,
                                     @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "15") int pageSize,
                                                                                        int deptId){
        return AjaxResult.success(attendanceLogService.departmentBookPageInfo(attStartTime,attEndTime,pageNum, pageSize,deptId));
    }
    /**
     * 查看个人本周时间总和
     * @return 时间
     */
    @PreAuthorize("@ss.hasPermi('attendance:log')")
    @GetMapping("/userWeekTime")
    @ResponseBody
    public AjaxResult userWeekTime(){
        return AjaxResult.success(attendanceLogService.userWeekTime());
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
        String filePath = "/var/temp_website/signlog/"; // 您的文件路径
        attendanceLogService.getFileByName(request, response, filePath, filename);
    }
}
