package tyut.selab.schedule.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.TimeFrame;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.enums.Status;
import tyut.selab.schedule.exception.RepetitiveRequestException;
import tyut.selab.schedule.mapper.IDisplayScheduleMapper;
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

    public static Set<Long> threads = new HashSet();

    /**
     * mapper对象
     */
    @Autowired
    private IUploadScheduleMapper iUploadScheduleMapper;

    @Autowired
    private IDisplayScheduleService iDisplayScheduleService;

    @Autowired
    private IDisplayScheduleMapper iDisplayScheduleMapper;

    /**
     * 上传课表
     *
     * @param uploadScheduleRequests 课表集合
     * @param userId                 用户id
     */
    @Override
    public void insertSchedule(List<UploadScheduleRequest> uploadScheduleRequests, Long userId) {
        synchronized (userId) {
            if (threads.contains(userId)) {
                throw new RepetitiveRequestException("数据正在存储中,请勿重复提交!");
            } else {
                threads.add(userId);
            }
        }
        new Thread(new UploadThread(userId, uploadScheduleRequests,iDisplayScheduleMapper)).start();
    }

    class UploadThread implements Runnable {

        private Long userId;
        private List<UploadScheduleRequest> uploadScheduleRequests;
        private IDisplayScheduleMapper iDisplayScheduleMapper;

        UploadThread(Long userId, List<UploadScheduleRequest> uploadScheduleRequests,IDisplayScheduleMapper iDisplayScheduleMapper) {
            this.userId = userId;
            this.uploadScheduleRequests = uploadScheduleRequests;
            this.iDisplayScheduleMapper = iDisplayScheduleMapper;
        }

        @Override
        public void run() {
            List<Schedule> schedules = new ArrayList<>();
            List<ScheduleDisplayResponse> schedulesByThisUser = iDisplayScheduleService.selectScheduleList(userId);

            //过滤重复数据
//            Set<TimeFrame> collect = schedulesByThisUser.stream().map(data -> new TimeFrame(data.getPeriod(), data.getWeek(), data.getWeekNo())).collect(Collectors.toSet());
//
//            for (int i = 0; i < uploadScheduleRequests.size(); i++) {
//                if (collect.contains(uploadScheduleRequests.get(i).toTimeFrame())) {
//                    uploadScheduleRequests.remove(i);
//                    i--;
//                }
//            }
//
//            if (uploadScheduleRequests.size() == 0) {
//                threads.remove(userId);
//                return;
//            }

            //伪删除
            //每次传回来数据就把本人以前上传的全部删除
            iDisplayScheduleMapper.deleteScheduleByUserId(userId);

            for (UploadScheduleRequest s : uploadScheduleRequests) {
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
            threads.remove(userId);
        }
    }
}
