import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.service.IDisplayMyHistoryVoteService;

import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
public class DisplayMyHistoryVoteMapperTest {
    @Test
    public void myVoteTest(@Autowired IDisplayMyHistoryVoteService service){
        List<VoteResult> list = service.displayMyHistory();
        System.out.println(list.get(0).getId());
    }

}
