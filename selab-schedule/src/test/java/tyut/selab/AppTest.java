package tyut.selab;

import com.ruoyi.common.core.domain.entity.SysUser;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tyut.selab.schedule.controller.ArrangeController;
import tyut.selab.schedule.domain.vo.ArrangePeriodRequest;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.service.impl.ArrangePeriodService;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void test(){
//        for(Week week: Week.values()){
//            for(Period period: Period.values()){
//                System.out.println(week);
//                System.out.println(period.getName());
//            }
//        }

//        Long userId = 45L;
//        Map<Long,Integer> map = new HashMap<Long,Integer>();
//        map.put(45L,4);
//        for(Map.Entry<Long,Integer> entry : map.entrySet()){
//            if(userId == entry.getKey()){
//                System.out.println("sdasd");
//            }
//        }

//        List<Integer>[][] userLists = new LinkedList[8][11];
//        userLists[0][0] = new LinkedList<>();
//        System.out.println(userLists[0][0].add(1));
//        for(Week week: Week.values()){
//            for(Period period: Period.values()) {
//                System.out.print(week.getId());
//                System.out.println(period.getId());
//                int id = week.getId();
//                int id1 = period.getId();
//                List<Integer> integers = userLists[id][id1];
//                integers.add(1);
//            }
//        }

//        HashMap<Long, Integer> longIntegerHashMap = new HashMap<>();
//
//        longIntegerHashMap.put(1L,5);
//        longIntegerHashMap.put(1L,4);
//        System.out.println(longIntegerHashMap.get(1L));
//
//        List<SysUser>[][] emptySchedule = new LinkedList[8][11];
//        //初始化课表，每一节课的空课的用户都为一个LinkedLink集合
//        for (Week week : Week.values()){
//            for (Period period : Period.values()){
//                emptySchedule[week.getId()][period.getId()] = new LinkedList<SysUser>();
//            }
//        }
//        System.out.println(emptySchedule[1].length);

        List<Long> longs = new LinkedList<>();
        longs.add(1L);
        longs.add(3L);
        longs.add(2L);
        System.out.println(longs);
        for (int i = 0; i < longs.size(); i++) {
            System.out.println(longs.get(i));
            longs.remove(longs.get(i));
        }
        System.out.println(longs);
    }
}
