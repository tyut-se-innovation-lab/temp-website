package tyut.selab.attendance.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.attendance.service.Impl.AttendanceServiceImpl;

import java.util.Date;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;
import static com.ruoyi.common.utils.SecurityUtils.getUsername;

@RestController
@RequestMapping("/selab/attendance")
public class AttendanceController {
    @Autowired
    AttendanceServiceImpl attendanceService;

    /**
     * 签到
     * @return 签到信息
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign')")
    @PostMapping("/sign")
    @ResponseBody
    public AjaxResult signIn(){
        return attendanceService.signIn()?
                AjaxResult.success("签到成功"):
                AjaxResult.error("签到失败");
    }

    /**
     * 是否允许签退
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign')")
    @GetMapping("/could")
    @ResponseBody
    public AjaxResult couldSignOut(){
        return AjaxResult.success(attendanceService.couleSignOut());
    }

    /**
     * 签退
     */
    @PostMapping ("/sign/out")
    @PreAuthorize("@ss.hasPermi('attendance:sign')")
    @ResponseBody
    public AjaxResult signOut(){
        return attendanceService.signOut()?
                AjaxResult.success("签退成功"):
                AjaxResult.error("签退失败");
    }
}
