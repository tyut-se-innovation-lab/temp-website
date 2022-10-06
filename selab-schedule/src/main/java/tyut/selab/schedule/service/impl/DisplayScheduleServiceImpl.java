package tyut.selab.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.mapper.IDisplayScheduleMapper;
import tyut.selab.schedule.service.IDisplayScheduleService;

import java.util.List;

/**
 * 课程信息  服务层实现
 *
 * @Author: Gulu
 * @Date: 2022/10/4 9:03
 */
@Service
public class DisplayScheduleServiceImpl implements IDisplayScheduleService
{
    @Autowired
    private IDisplayScheduleMapper scheduleMapper;

    /**
     * 查询课程信息集合
     *
     * @param schedule 课程信息
     * @return 课程信息集合
     */
    @Override
    public List<Schedule> selectScheduleList(Schedule schedule) {
        return scheduleMapper.selectScheduleList(schedule);
    }
}
