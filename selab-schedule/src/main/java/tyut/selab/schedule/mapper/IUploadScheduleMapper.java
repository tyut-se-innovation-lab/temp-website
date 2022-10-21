package tyut.selab.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tyut.selab.schedule.domain.po.Schedule;

import java.util.List;


/**
 * @author Big_bai on 2022/10/4
 *
 */
public interface IUploadScheduleMapper {
    /**
     * 上传课表
     * @param schedules 课表集合
     * @return 1
     */
    int insertSchedule(List<Schedule> schedules);
}
