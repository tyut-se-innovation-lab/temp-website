package tyut.selab.schedule.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@Service
public class UploadScheduleService {
    @Autowired
    private IUploadScheduleMapper iUploadScheduleMapper;

    public void insertSchedule(List<Schedule> schedules){
        for (Schedule schedule:schedules) {
            iUploadScheduleMapper.insertSchedule(schedule);
        }
    }

}
