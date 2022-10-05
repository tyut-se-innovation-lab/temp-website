package tyut.selab.schedule.mapper;

import tyut.selab.schedule.domain.po.Schedule;

import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/10/1 13:23
 */
public interface IDisplayScheduleMapper {
    /**
     * 查询课表
     * @param schedule 查询条件
     * @return 课程信息
     */
     public Schedule selectSchedule(Schedule schedule);

    /**
     * 查询课程列表
     * @param schedule 查询条件
     * @return 课程信息集合
     */
     public List<Schedule> selectScheduleList(Schedule schedule);
}
