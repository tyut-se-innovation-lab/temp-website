package ruoyi_attendace;

import com.ruoyi.RuoYiApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tyut.selab.attendance.mapper.AttendanceLogMapper;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuoYiApplication.class)
public class AttendanceLogMapperTest {
    @Autowired
    private AttendanceLogMapper attendanceLogMapper;

    @Test
    public void deptUserIdTest(){
        List<Integer> integers =
                attendanceLogMapper.deptUserId(210);
        integers.forEach(e->{
            System.out.println(e);
        });
    }
}
