package tyut.selab.schedule.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.schedule.domain.po.Schedule;

import java.util.List;

/**
 * @Author: Gulu
 * @Date: 2022/10/1 13:23
 */
public interface IDisplayScheduleMapper {
    /**
     * 查询课程列表
     * @param userId 查询条件
     * @return 课程信息集合
     */
     public List<Schedule> selectScheduleList(Long userId);

    /**
     * 删除我的课表
     * @param id 课表信息唯一标识
     * @return 1
     */
    int deleteScheduleById(Long id);

    /**
     * 根据用户id删除所有本用户所上传的全部课表
     * @param userId
     * @return
     */
    int deleteScheduleByUserId(Long userId);
}
