package tyut.selab.schedule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.service.impl.UploadScheduleService;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@Controller
public class UploadController {
    @Autowired
    private UploadScheduleService uploadScheduleService;

    @PostMapping("/selab/Schedule")
    @PreAuthorize("@ss.hasAnyPermi('schedule:upload')")
    public void uploadSchedule(@RequestBody List<Schedule> schedules){
        uploadScheduleService.insertSchedule(schedules);
    }

}
