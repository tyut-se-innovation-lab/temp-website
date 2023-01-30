import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.service.impl.DisplayVoteResultServiceImpl;

import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
public class DisplayVoteResultMapperTest {
    @Test
    public void VoteTest(@Autowired FindInfoDBMapper findInfoDBMapper){
        List<PoVoteOption> voteOptionByParentId = findInfoDBMapper.getVoteOptionByParentId(1L);
        voteOptionByParentId.forEach(System.out::println);
    }
    @Test
    public void displayVoteResultTest(@Autowired DisplayVoteResultServiceImpl service){
        Questionnaire questionnaire = service.displayVoteResult(1L,"2021001111");
        System.out.println(questionnaire);
    }

}
