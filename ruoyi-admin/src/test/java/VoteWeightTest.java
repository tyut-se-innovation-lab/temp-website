import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.service.IWeightControlService;

/**
 * @author Big_bai on 2022/11/22
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class VoteWeightTest {
    @Autowired
    IWeightControlService weightControlService;

    @Test
    public void getWeightByIdTest(){
        weightControlService.getWeightByUserId("2");
    }
    @Test
    public void setGetWeightTest(){
       weightControlService.setVoteWeight(null);
    }
}
