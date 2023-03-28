package tyut.selab.attendance.task;

import org.springframework.beans.factory.annotation.Autowired;
import tyut.selab.attendance.mapper.AttendanceLogMapper;
import tyut.selab.attendance.mapper.AttendanceMapper;
import tyut.selab.attendance.service.Impl.GenerateLogServiceImpl;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Gulu
 * @Date: 2023/3/27 15:55
 */
public class ScheduledTask {

    private ScheduledExecutorService executor;

    public ScheduledTask() {
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * 开始定时任务
     */
    public void start() {
        // 设置每周日晚10点的执行时间，执行周期为7天，即每周执行一次
        LocalDateTime sundayNight = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.SUNDAY)).withHour(22).withMinute(0).withSecond(0).withNano(0);
        long initialDelay = ChronoUnit.MILLIS.between(LocalDateTime.now(), sundayNight);
        long period = 7 * 24 * 60 * 60 * 1000L;
        executor.scheduleAtFixedRate(new SundayNightTask(), initialDelay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止定时任务
     */
    public void stop() {
        executor.shutdown();
    }

    /**
     * 执行的定时任务
     */
    private static class SundayNightTask implements Runnable {
        @Override
        public void run() {
            GenerateLogServiceImpl generateLogService = new GenerateLogServiceImpl();

            generateLogService.deleteThisWeekLog(); //删除本周签到日志记录
        }
    }
}
