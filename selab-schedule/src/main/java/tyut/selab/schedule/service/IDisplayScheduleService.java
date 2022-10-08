package tyut.selab.schedule.service;

import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;

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
     * @param userId 课程信息
     * @return 课程信息集合
     */
    public List<ScheduleDisplayResponse> selectScheduleList(Long userId);
}
