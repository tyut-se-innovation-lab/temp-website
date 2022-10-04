package tyut.selab.schedule.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tyut.selab.schedule.domain.po.Schedule;


/**
 * @author Big_bai on 2022/10/4
 *
 */
@Repository
@Mapper
public interface IUploadScheduleMapper {
    /**
     * 上传课表
     * @param schedule
     * @return
     */
    int insertSchedule(Schedule schedule);
}
