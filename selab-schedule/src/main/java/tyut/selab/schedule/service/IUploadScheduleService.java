package tyut.selab.schedule.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tyut.selab.schedule.domain.po.Schedule;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@Mapper
@Repository
public interface IUploadScheduleService {
    /**
     * 添加课表
     * @param schedule
     */
    public void insertSchedule(List<Schedule> schedule,Long userId);
}
