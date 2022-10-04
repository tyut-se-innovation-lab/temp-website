package tyut.selab.schedule.service;

import tyut.selab.schedule.domain.po.Schedule;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
public interface IUploadScheduleService {
    /**
     * 添加课表
     * @param schedules
     */
    public void insertSchedule(List<Schedule> schedules);
}
