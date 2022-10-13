package tyut.selab.schedule.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.service.IDisplayScheduleService;
import tyut.selab.schedule.service.impl.UploadScheduleService;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@RestController
@RequestMapping("/selab/schedule/mine")
public class MineScheduleController extends BaseController {

    @Autowired
    private IDisplayScheduleService displayScheduleService;

    @Autowired
    private UploadScheduleService uploadScheduleService;


    /**
     * 上传课表
     */
    @PostMapping("/upload")
    @PreAuthorize("@ss.hasPermi('schedule:mine')")
    public AjaxResult uploadSchedule(@RequestBody List<UploadScheduleRequest> uploadScheduleRequests) {
        uploadScheduleService.insertSchedule(uploadScheduleRequests, getUserId());
        return AjaxResult.success("正在上传，请稍后查看");
    }

    @GetMapping("/display")
    @PreAuthorize("@ss.hasPermi('schedule:mine')")
    @ResponseBody
    public AjaxResult displaySchedule() {
        return AjaxResult.success(displayScheduleService.selectScheduleList(getUserId()));
    }

}
