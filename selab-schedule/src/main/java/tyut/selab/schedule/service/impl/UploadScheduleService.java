package tyut.selab.schedule.service.impl;

import com.alibaba.fastjson2.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.domain.vo.ScheduleDisplayResponse;
import tyut.selab.schedule.domain.vo.UploadScheduleRequest;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Status;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.exception.RepetitiveRequestException;
import tyut.selab.schedule.mapper.IDisplayScheduleMapper;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;
import tyut.selab.schedule.service.IDisplayScheduleService;
import tyut.selab.schedule.service.IUploadScheduleService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

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

    @Autowired
    private HttpClient httpClient;

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
        new Thread(new UploadThread(userId, uploadScheduleRequests, iDisplayScheduleMapper)).start();
    }

    @Override
    public void crawlScheduleIdentifiedByCookie(Long userId, String token, String sessionId) {
        HttpRequest request = assembleHttpRequest(token, sessionId);
        try {
            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            String body = unzipInputStreamToString(response.body());
            List<Map<String, String>> list = parseRawData(body);
            List<UploadScheduleRequest> requestData = encapsulateRowDataToSchedule(list);
            insertSchedule(requestData,userId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<UploadScheduleRequest> encapsulateRowDataToSchedule(List<Map<String, String>> rowData) {
        return rowData.stream()
                .flatMap(data -> {
                    LinkedList<UploadScheduleRequest> schedules = new LinkedList<>();
                    String weekNos = data.get("Zcsm");
                    String[] weekNoEdge = weekNos.substring(0, weekNos.length() - 1).split("-");
                    String courseName = data.get("Kcm");
                    int week = Integer.parseInt(data.get("Skxq"));
                    int startPeriod = Integer.parseInt(data.get("Skjc"));
                    int durationPeriod = Integer.parseInt(data.get("Cxjc"));

                    int startWeekNo = Integer.parseInt(weekNoEdge[0]);
                    int endWeekNo = Integer.parseInt(weekNoEdge[1]);

                    for (int weekNo = startWeekNo; weekNo <= endWeekNo; weekNo++) {
                        for (int duration = 0; duration < durationPeriod; duration++) {
                            UploadScheduleRequest schedule = new UploadScheduleRequest();
                            schedule.setCourseTitle(courseName);
                            schedule.setWeekNo(WeekNo.getWeekNoById(weekNo));
                            schedule.setWeek(Week.getWeekById(week));
                            schedule.setPeriod(Period.getPeriodById(startPeriod + durationPeriod));
                            schedules.add(schedule);
                        }
                    }

                    return schedules.stream();
                })
                .collect(Collectors.toList());
    }

    private List<Map<String, String>> parseRawData(String rowData) {
        HashMap hashMap = JSON.parseObject(rowData, HashMap.class);;
        return (List<Map<String, String>>) hashMap.entrySet().stream()
                .map(obj -> ((Map.Entry) obj).getValue())
                .flatMap(value -> ((List<Map<String, String>>) value).stream())
                .collect(Collectors.toList());
    }

    private String unzipInputStreamToString(InputStream inputStream) {
        try {
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
            byte[] bytes = gzipInputStream.readAllBytes();
            return new String(bytes);
        } catch (IOException e) {
            throw new RuntimeException("认证失败");
        }
    }

    private HttpRequest assembleHttpRequest(String token, String sessionId) {
        String cookie = new StringBuilder().append("__RequestVerificationToken=")
                .append(token)
                .append("; ")
                .append("ASP.NET_SessionId=")
                .append(sessionId).toString();
        URI uri = null;
        try {
            uri = new URI("http://jxgl1.tyut.edu.cn/Tresources/A1Xskb/GetXsKb");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return HttpRequest.newBuilder(uri)
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept", "application/json, text/javascript, */*; q=0.01")
                .header("Referer", "http://jxgl1.tyut.edu.cn/Tresources/A1Xskb/XsKbIndex")
                .header("Origin", "http://jxgl1.tyut.edu.cn")
                .header("Content-Typy", "application/x-www-form-urlencoded; charset=UTF-8")
                .header("X-Requested-With", "XMLHttpRequest")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.0 Safari/605.1.15")
                .header("Cookie", cookie)
                .header("Accept-Language", "zh-CN,zh-Hans;q=0.9")
                .POST(HttpRequest.BodyPublishers.ofString("zxjxjhh="))
                .build();
    }

    class UploadThread implements Runnable {

        private Long userId;
        private List<UploadScheduleRequest> uploadScheduleRequests;
        private IDisplayScheduleMapper iDisplayScheduleMapper;

        UploadThread(Long userId, List<UploadScheduleRequest> uploadScheduleRequests, IDisplayScheduleMapper iDisplayScheduleMapper) {
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
