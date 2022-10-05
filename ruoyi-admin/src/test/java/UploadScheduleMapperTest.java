import com.ruoyi.RuoYiApplication;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysMenuService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.PackagePropertiesUnmarshaller;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Status;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.mapper.IDisplayScheduleMapper;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;

import java.util.Date;
import java.util.List;

/**
 * @author Big_bai on 2022/10/4
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class UploadScheduleMapperTest {
    @Autowired
    IDisplayScheduleMapper iDisplayScheduleMapper;
    @Autowired
    IUploadScheduleMapper iUploadScheduleMapper ;

//    @Test
//    public void testMapper(){
//        System.out.println(iUploadScheduleMapper);
//    }
//
//    @Test
//    public void testService(@Autowired ISysMenuService service){
//        System.out.println(service);
//    }

//    @Test
//    public void testSysMapper(@Autowired SysConfigMapper configMapper){
//        System.out.println(configMapper);
//    }

    @Test
    public void test() {
        Schedule schedule = new Schedule();
//        schedule.setId(500L);
        schedule.setUserId(10L);
        schedule.setCourseTitle("数据结构");
        schedule.setCreateTime(new Date());
        schedule.setPeriod(Period.EIGHTH);
        schedule.setWeek(Week.WEDNESDAY);
        schedule.setWeekNo(WeekNo.EIGHTH_TERM);
        schedule.setUpdateTime(new Date());
        schedule.setStatus(Status.ENABLE);

        //iUploadScheduleMapper.insertSchedule(schedule);
        List user = iDisplayScheduleMapper.selectScheduleList(schedule);
        System.out.println(user);
    }

//    @Test
//    public void testSqlSessionFactory(@Autowired SqlSessionFactory sqlFactory){
//        SqlSession sqlSession = sqlFactory.openSession();
//        IUploadScheduleMapper mapper = sqlSession.getMapper(IUploadScheduleMapper.class);
//        System.out.println(mapper);
//
//        Schedule schedule = new Schedule();
//        schedule.setId(500L);
//        schedule.setUserId(10L);
//        schedule.setCourseTitle("aaa");
//        schedule.setCreateTime(new Date());
//        schedule.setPeriod(Period.FIFTH);
//        schedule.setWeek(Week.FRIDAY);
//        schedule.setWeekNo(WeekNo.EIGHTH_TERM);
//        schedule.setUpdateTime(new Date());
//        schedule.setStatus(Status.ENABLE);
//
//        int i = mapper.insertSchedule(schedule);
//        System.out.println(i);
//    }
}
