package tyut.selab.schedule.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.service.IDisplayScheduleService;
import java.util.List;

/**
 * 查询我的课程信息
 *
 * @Author: Gulu
 * @Date: 2022/10/4 14:38
 */
@Controller
public class DisplayScheduleController extends BaseController {
    @Autowired
    private IDisplayScheduleService displayScheduleService;
    @GetMapping("/selab/schedule/display")
    @PreAuthorize("@ss.hasAnyPermi('schedule:mine')")
    @ResponseBody
    public List<ScheduleDisplayResponse> displaySchedule(){
        return displayScheduleService.selectScheduleList(getUserId());
    }
}
