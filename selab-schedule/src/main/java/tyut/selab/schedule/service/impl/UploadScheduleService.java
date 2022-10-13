package tyut.selab.schedule.service.impl;

import com.ruoyi.framework.web.domain.server.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Status;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;
import tyut.selab.schedule.service.IDisplayScheduleService;
import tyut.selab.schedule.service.IUploadScheduleService;

import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private IDisplayScheduleService iDisplayScheduleService;

    /**
     * 上传课表
     * @param uploadScheduleRequests 课表集合
     * @param userId 用户id
     */
    @Override
    public void insertSchedule(List<UploadScheduleRequest> uploadScheduleRequests, Long userId) {
        Thread t = new Thread(new UploadThread(iUploadScheduleMapper,iDisplayScheduleService,userId,uploadScheduleRequests));
        t.start();
    }

}

class UploadThread implements Runnable{

    private IUploadScheduleMapper iUploadScheduleMapper;
    private IDisplayScheduleService iDisplayScheduleService;
    private Long userId;
    private List<UploadScheduleRequest> uploadScheduleRequests;
    UploadThread(IUploadScheduleMapper iUploadScheduleMapper,IDisplayScheduleService iDisplayScheduleService,Long userId,List<UploadScheduleRequest> uploadScheduleRequests){
        this.iUploadScheduleMapper = iUploadScheduleMapper;
        this.iDisplayScheduleService = iDisplayScheduleService;
        this.userId = userId;
        this.uploadScheduleRequests = uploadScheduleRequests;
    }
    @Override
    public void run() {
        List<Schedule> schedules = new ArrayList<>();
        List<ScheduleDisplayResponse> schedulesByThisUser = iDisplayScheduleService.selectScheduleList(userId);

        Set<TimeFrame> collect = schedulesByThisUser.stream()
                .map(data -> new TimeFrame(data.getPeriod(), data.getWeek(), data.getWeekNo()))
                .collect(Collectors.toSet());

        for(int i = 0;i<uploadScheduleRequests.size();i++){
            if(collect.contains(uploadScheduleRequests.get(i).toTimeFrame())){
                uploadScheduleRequests.remove(i);
                i--;
            }
        }

        if(uploadScheduleRequests.size()==0){
            return;
        }

        for(UploadScheduleRequest s:uploadScheduleRequests){
            Schedule schedule = new Schedule();
            schedule.setUserId(userId);
            schedule.setCourseTitle(s.getCourseTitle());
            schedule.setCreateTime(new Date());
            schedule.setPeriod(s.getPeriod());
            schedule.setWeek(s.getWeek());
            schedule.setWeekNo(s.getWeekNo());
            schedule.setStatus(Status.ENABLE);
            schedules.add(schedule);
        }
        iUploadScheduleMapper.insertSchedule(schedules);
    }
    }
