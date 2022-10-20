package tyut.selab.schedule.service.impl;

import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.mapper.IDisplayScheduleMapper;
import tyut.selab.schedule.service.IDisplayScheduleService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程信息  服务层实现
 *
 * @Author: Gulu
 * @Date: 2022/10/4 9:03
 */
@Service
public class DisplayScheduleServiceImpl implements IDisplayScheduleService {
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
        long startMillis = System.currentTimeMillis();
        List<ScheduleDisplayResponse> scheduleDisplayResponses = new ArrayList<>();
        List<Schedule> schedules = scheduleMapper.selectScheduleList(userId);
        for (Schedule sc : schedules) {
            ScheduleDisplayResponse scheduleDisplayResponse = new ScheduleDisplayResponse();
            scheduleDisplayResponse.setId(sc.getId());
            scheduleDisplayResponse.setWeek(sc.getWeek());
            scheduleDisplayResponse.setWeekNo(sc.getWeekNo());
            scheduleDisplayResponse.setPeriod(sc.getPeriod());
            scheduleDisplayResponse.setCourseTitle(sc.getCourseTitle());
            logger.debug(scheduleDisplayResponses.toString());
            scheduleDisplayResponses.add(scheduleDisplayResponse);
        }
        scheduleDisplayResponses = scheduleDisplayResponses.stream()
                .sorted((a, b) -> {
                    int var1 = a.getPeriod().getId() - b.getPeriod().getId();
                    int var2 = a.getWeek().getId() - b.getWeek().getId();
                    int var3 = StringUtils.compare(a.getCourseTitle(), b.getCourseTitle());
                    return var1 * 100 + var2 * 10 + var3;
                })
                .collect(Collectors.toList());
        long entMillis = System.currentTimeMillis();
        logger.info("查询课表数据，总计时长为: {}毫秒", (entMillis - startMillis));
        return scheduleDisplayResponses;
    }

    /**
     * 删除我的课程信息
     *
     * @param ids 课程唯一id集合
     * @return 删除成功条数
     */
    @Override
    public int deleteSchedule(List<Long> ids) {
        if (!ids.isEmpty()) {
            int i = ids.size();
            for (Long id : ids) {
                int number = scheduleMapper.deleteScheduleById(id);
                if (number == 0) i--;
            }
            return i;
        }
        return 0;
    }

}