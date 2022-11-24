import com.ruoyi.RuoYiApplication;
import com.ruoyi.system.service.ISysConfigService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.service.IWithdrowVoteService;
import tyut.selab.vote.service.impl.WithdrowVoteServiceImpl;

import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@SpringBootTest(classes = RuoYiApplication.class)
public class FindInfoDBMapperTest {
    @Test
    public void FindInfoDBMapper(@Autowired IWithdrowVoteService iWithdrowVoteService) throws Exception {
        List<VoteInfo> voteInfoById = iWithdrowVoteService.findVoteInfoById(1);


    }
}
