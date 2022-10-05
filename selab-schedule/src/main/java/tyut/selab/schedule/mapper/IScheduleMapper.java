package tyut.selab.schedule.mapper;

import tyut.selab.schedule.domain.po.Schedule;

import java.util.List;

public interface IScheduleMapper {

    List<Schedule> select(Schedule schedule);

    /**
     * 添加新课表
     * @param schedules
     * @return
     */

    Integer insert(List<Schedule> schedules);

    Integer delete(Schedule schedule);

    Integer update(Schedule schedule);
}
