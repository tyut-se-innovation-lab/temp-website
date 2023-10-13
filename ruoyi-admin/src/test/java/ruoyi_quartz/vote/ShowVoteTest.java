package ruoyi_quartz.vote;

import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.tools.impl.RSATool;

/**
 * @author Big-Bai
 **/
@SpringBootTest(classes = RuoYiApplication.class)
public class ShowVoteTest {
    @Autowired
    IShowDetailedVoteListService service;

    @Test
    public void showVoteTest(){
        Questionnaire questionnaire = service.showDetailedVote(1L);
        System.out.println(questionnaire.toString());
        String decrypt = RSATool.decrypt("fX38jwOg1T/uFD8GY9xa95aNfqS8+TgKHMPaDmH/oZD0JM3kRIe9EstSpVMUnCvH3/9zMTSZvKoy1iZEvC1CKw==");
        System.out.println(decrypt);
    }
}
