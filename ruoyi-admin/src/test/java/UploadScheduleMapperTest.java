import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;

import java.util.Date;

/**
 * @author Big_bai on 2022/10/4
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class UploadScheduleMapperTest {
    @Autowired
    IUploadScheduleMapper iUploadScheduleMapper;
    @Test
    public void test() {
        System.out.println(iUploadScheduleMapper);
        Schedule schedule = new Schedule();
        schedule.setUserId(10L);
        schedule.setCourseTitle("aaa");
        schedule.setCreateTime(new Date());
        schedule.setPeriod(Period.FIFTH);
        schedule.setWeek(Week.FRIDAY);
        schedule.setWeekNo(WeekNo.EIGHTH_TERM);

        int i = iUploadScheduleMapper.insertSchedule(schedule);
        System.out.println(i);
    }
}
