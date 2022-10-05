package tyut.selab.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;
import tyut.selab.schedule.service.IUploadScheduleService;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@Service
public class UploadScheduleService implements IUploadScheduleService {

    @Autowired
    private IUploadScheduleMapper iUploadScheduleMapper;

    @Override
    public void insertSchedule(List<Schedule> schedules,Long userId){
        for (Schedule schedule:schedules) {
            schedule.setUserId(userId);
            iUploadScheduleMapper.insertSchedule(schedule);
        }
    }

}
