package tyut.selab.schedule.service;

import tyut.selab.schedule.domain.po.Schedule;

import java.util.List;

/**
 * 课程信息  服务层
 *
 * @Author: Gulu
 * @Date: 2022/10/4 9:02
 */
public interface IDisplayScheduleService {
    /**
     * 查询课程信息
     * @param schedule 课程信息
     * @return 课程信息集合
     */
    public List<Schedule> selectScheduleList(Schedule schedule);
}
