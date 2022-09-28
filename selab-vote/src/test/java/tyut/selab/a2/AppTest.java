package tyut.selab.a2;

import org.junit.Test;

import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void showDateTime(){

        System.out.println(new Date().getTime());
    }
}
