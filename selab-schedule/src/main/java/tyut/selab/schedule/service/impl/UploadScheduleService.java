package tyut.selab.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Status;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;
import tyut.selab.schedule.service.IUploadScheduleService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@Service
public class UploadScheduleService implements IUploadScheduleService {

    /**
     * mapper对象
     */
    @Autowired
    private IUploadScheduleMapper iUploadScheduleMapper;

    /**
     * 上传课表
     * @param uploadScheduleRequests 课表集合
     * @param userId 用户id
     */
    @Override
    public void insertSchedule(List<UploadScheduleRequest> uploadScheduleRequests, Long userId){
        List<Schedule> schedules = new ArrayList<>();
        for(UploadScheduleRequest s:uploadScheduleRequests){
            Schedule schedule = new Schedule();
            schedule.setUserId(userId);
            schedule.setCourseTitle(s.getCourseTitle());
            schedule.setCreateTime(new Date());
            schedule.setPeriod(s.getPeriod());
            schedule.setWeek(s.getWeek());
            schedule.setWeekNo(s.getWeekNo());
            schedule.setStatus(Status.ENABLE);
            schedules.add(schedule);
        }
        iUploadScheduleMapper.insertSchedule(schedules);
    }

}
