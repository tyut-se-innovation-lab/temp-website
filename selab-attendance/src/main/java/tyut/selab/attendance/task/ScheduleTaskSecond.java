package tyut.selab.attendance.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tyut.selab.attendance.mapper.GenerateLogMapper;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Gulu
 * @Date: 2023/3/28 20:02
 */
@Component
public class ScheduleTaskSecond {

    static{
        new ScheduleTaskSecond().start();
    }

    private ScheduledExecutorService executor;

    public ScheduleTaskSecond() {
        executor = Executors.newSingleThreadScheduledExecutor();
    }

    /**
     * 开始定时任务
     */
    public void start() {
        // 设置每日晚11点的执行时间，执行周期为1天，即每天执行一次
        LocalDateTime everyDay = LocalDateTime.now().withHour(12).withMinute(05).withSecond(50).withNano(0);
        long initialDelay = ChronoUnit.MILLIS.between(LocalDateTime.now(), everyDay);
        long period = 24 * 60 * 60 * 1000L;
        executor.scheduleAtFixedRate(new everyDayNightTask(), initialDelay, period, TimeUnit.MILLISECONDS);
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
    private static class everyDayNightTask implements Runnable {

        @Autowired
        GenerateLogMapper generateLogMapper;

        @Override
        public void run() {
            System.out.println("定时任务执行了");
            generateLogMapper.deleteInvalidData();
        }
    }
}
