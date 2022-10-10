package tyut.selab.schedule.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.schedule.domain.vo.ArrangePeriodRequest;
import tyut.selab.schedule.service.impl.ArrangePeriodService;

/**
 * @author 冯洋
 * @version 1.0
 */

@RestController
@RequestMapping("/selab/schedule")
public class ArrangeController {
    @Autowired
    private ArrangePeriodService arrangePeriodService;

    @PreAuthorize("@ss.hasPermi('schedule:arrangement')")
    @RequestMapping("/arrange")
    public AjaxResult arrangePeriod(ArrangePeriodRequest arrangePeriodRequest) {
        return AjaxResult.success(arrangePeriodService.arrangePeriod(arrangePeriodRequest));
    }
}