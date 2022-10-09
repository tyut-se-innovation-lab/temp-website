package tyut.selab.schedule.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.mapper.IDisplayScheduleMapper;
import tyut.selab.schedule.service.IDisplayScheduleService;

import java.util.ArrayList;
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
    private static Logger logger = LoggerFactory.getLogger(DisplayScheduleServiceImpl.class);
    @Autowired
    private IDisplayScheduleMapper scheduleMapper;

    /**
     * 查询课程信息集合
     *
     * @param userId 课程信息
     * @return 课程信息集合
     */
    @Override
    public List<ScheduleDisplayResponse> selectScheduleList(Long userId) {
        List<ScheduleDisplayResponse> scheduleDisplayResponses = new ArrayList<>();
        List<Schedule> schedules = scheduleMapper.selectScheduleList(userId);
        for (Schedule sc:schedules) {
            ScheduleDisplayResponse scheduleDisplayResponse = new ScheduleDisplayResponse();
            scheduleDisplayResponse.setWeek(sc.getWeek().getId());
            scheduleDisplayResponse.setWeekNo(sc.getWeekNo().getId());
            scheduleDisplayResponse.setPeriod(sc.getPeriod().getId());
            scheduleDisplayResponse.setCourseTitle(sc.getCourseTitle());
            logger.debug(scheduleDisplayResponses.toString());
            scheduleDisplayResponses.add(scheduleDisplayResponse);
        }
        return  scheduleDisplayResponses;
    }
}