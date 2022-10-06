package tyut.selab.schedule.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@Mapper
@Repository
public interface IUploadScheduleService {
    /**
     * 上传课表
     * @param uploadScheduleRequests 课表集合
     * @param userId 用户id
     */
    public void insertSchedule(List<UploadScheduleRequest> uploadScheduleRequests, Long userId);
}
