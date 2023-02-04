import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.service.impl.DisplayAllVoteImpl;
import tyut.selab.vote.service.impl.DisplayVoteResultServiceImpl;
import tyut.selab.vote.service.impl.WeightControlService;

import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
public class DisplayVoteResultMapperTest {
    @Test
    public void VoteTest(@Autowired FindInfoDBMapper findInfoDBMapper){
        List<PoVoteOption> voteOptionByParentId = findInfoDBMapper.getVoteOptionByParentId(1L);
        voteOptionByParentId.forEach(System.out::println);
    }
    @Test
    public void displayVoteResultTest(@Autowired DisplayVoteResultServiceImpl service, @Autowired FindInfoDBMapper findInfoDBMapper, @Autowired WeightControlService weightControlService,@Autowired DisplayAllVoteImpl displayAllVote){
        //历史粗略
        List<Questionnaire> list1 = displayAllVote.displayAllVote(2021001111L);
        List<Questionnaire> list2 = displayAllVote.displayAllUsefulVotes(2021001111L);
        List<Questionnaire> list3 = displayAllVote.displayMyJoinVote(2021001111L);
        List<Questionnaire> list4 = displayAllVote.displayMyStartVote(2021001111L);
        list1.forEach(System.out::println);
        list2.forEach(System.out::println);
        list3.forEach(System.out::println);
        list4.forEach(System.out::println);
        //详细
        Questionnaire questionnaire1 = service.displayVoteGoing(1L, 2021001111L);
        Questionnaire questionnaire2 = service.displayVoteHistory(1L, 2021001111L);
        System.out.println(questionnaire1);
        System.out.println(questionnaire2);
    }
    @Test
    public void displayVoteResultTest1(@Autowired DisplayAllVoteImpl service){
        List<Questionnaire> questionnaireList = service.displayMyJoinVote(2021001111L);
        System.out.println(questionnaireList);
    }

}
