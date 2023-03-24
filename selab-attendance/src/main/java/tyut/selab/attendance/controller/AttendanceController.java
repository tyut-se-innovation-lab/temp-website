package tyut.selab.attendance.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/selab/attendance")
public class AttendanceController {

    /**
     * 签到
     * @return 签到信息
     */
    @PreAuthorize("@ss.hasPermi('attendance:sign')")
    @PostMapping("/sign")
    @ResponseBody
    public AjaxResult signIn(){
        return AjaxResult.success(new Date()+"成功签到");
    }

    /**
     * 是否允许签退
     */
    @GetMapping("/could")
    @ResponseBody
    public AjaxResult couldSignOut(){
        return AjaxResult.success(true);
    }

    /**
     * 签退
     */
    @PostMapping ("/sign/out")
    @PreAuthorize("@ss.hasPermi('attendance:sign')")
    @ResponseBody
    public AjaxResult signOut(){
        return AjaxResult.success(new Date()+"成功签退");
    }
}
