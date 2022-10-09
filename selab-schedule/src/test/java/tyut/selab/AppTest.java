package tyut.selab;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import tyut.selab.schedule.controller.ArrangeController;
import tyut.selab.schedule.domain.vo.ArrangePeriodRequest;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.service.impl.ArrangePeriodService;

import java.util.HashMap;
import java.util.Map;

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

        Map<Long,Integer> map = new HashMap<Long,Integer>();

        map.put(2002L,50);
        map.put(2003L,60);

        System.out.println(map.containsKey(2002L));

        System.out.println(map.get(2002L));
        System.out.println(map.entrySet());
    }
}
