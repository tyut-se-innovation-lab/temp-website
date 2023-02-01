import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.IDisplayAllVoteService;
import tyut.selab.vote.service.IWithdrowVoteService;

import java.util.Iterator;
import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
public class MyVoteTest {
    @Autowired
    IDisplayAllVoteService iDisplayAllVoteService;
    @Autowired
    private IWithdrowVoteService withdrowVoteService;

    @Test
    public void displayMyALLVote(){
        System.out.println("===========================================");
        System.out.println(iDisplayAllVoteService.displayMyJoinVote("2"));
        System.out.println("===========================================");
    }

    @Test
    public void myLaunchVote(){
        System.out.println("===========================================");
        System.out.println(iDisplayAllVoteService.displayMyStartVote("1"));
        System.out.println("===========================================");
    }

    @Test
    public void deleteVote(){
        System.out.println("===========================================");
        System.out.println(withdrowVoteService.delectVoteInfoById(Long.valueOf("1")));
        System.out.println("===========================================");
    }

    @Test
    public void displayMyJoinVoteService() {
        List<Questionnaire> questionnaires = iDisplayAllVoteService.displayMyJoinVote("1");
        Iterator<Questionnaire> iterator = questionnaires.iterator();
        System.out.println("===========================================");
        while (iterator.hasNext()) {
            Questionnaire next = iterator.next();
            System.out.println(next);
        }
        System.out.println("===========================================");
    }

    /**
     * 提前结束vote
     */
    @Test
    public void finishVote(){
        System.out.println("===========================================");
        System.out.println(iDisplayAllVoteService.finishVote(String.valueOf(1)));
        System.out.println("===========================================");
    }
}
