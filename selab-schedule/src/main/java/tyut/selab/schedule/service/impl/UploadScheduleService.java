package tyut.selab.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;
import tyut.selab.schedule.service.IUploadScheduleService;

import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@Service
public class UploadScheduleService implements IUploadScheduleService {

    /**
     * mapper对象
     */
    @Autowired
    private IUploadScheduleMapper iUploadScheduleMapper;

    /**
     * 天上传课表
     * @param uploadScheduleRequests 课表集合
     * @param userId 用户id
     */
    @Override
    public void insertSchedule(List<UploadScheduleRequest> uploadScheduleRequests, Long userId){

    }

}
