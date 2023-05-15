package tyut.selab.a2;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.impl.WeightControlService;

import java.util.Date;

/**
 * Unit test for simple App.
 */
@SpringBootTest
public class AppTest {
    @Test
    public void showDateTime(){

        System.out.println(new Date().getTime());
    }
    @Autowired
    private IDisplayVoteResultService displayVoteResultService;

    @Test
    public void DisplayHisVote (){
        int userID = 1;
        int voteID =1;
//        displayVoteResultService.displayVoteResult((long) voteID, (long) userID);


    }
    @Test
    public void IDisplayVoteResultServiceTest(){
        int userID =01;
        int voteId=02;

        DisplayVoteResultServiceImpl displayVoteResultService = new DisplayVoteResultServiceImpl();
//        System.out.println(displayVoteResultService.displayVoteResult((long) userID, (long) voteId));
    }
    @Autowired
    FindInfoDBMapper findInfoDBMapper;
    @Autowired
    FindInfoDBMapper displayAllVoteMapper;
    @Autowired
    WeightControlService weight;

    @Test
    public void DisplayVoteResultServiceimpl(){
        int userID = 1;
        int voteID =1;

        Questionnaire questionnaire= new Questionnaire();
        questionnaire.setId((long) voteID);
        questionnaire.getId();

        IDisplayVoteResultService iDisplayVoteResultService= new DisplayVoteResultServiceImpl();
//        iDisplayVoteResultService.displayVoteGoing((long) userID, (long) voteID);

    }
}
