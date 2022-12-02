import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.service.ILaunchVoteService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 上传课表的测试
 * @author Big_bai on 2022/12/2
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class LaunchVoteTest {
    @Autowired
    ILaunchVoteService launchVoteService;

    @Test
    public void launchVoteTest(){
        VoteInfo voteInfo = new VoteInfo();
        List<PoVoteOption> voteOptions = new ArrayList<>();

        voteInfo.setUserId(100L);
        voteInfo.setTitle("傻儿子吉吉");
        voteInfo.setContent("吉吉他爹");
        voteInfo.setStatus("1");
        voteInfo.setDeadline(new Date());
        voteInfo.setCreateTime(new Date());
        voteInfo.setWeight(1L);

        PoVoteOption que1 = new PoVoteOption();
        que1.setOptionType("Q");
        que1.setContent("是不是我");

        PoVoteOption opt1 = new PoVoteOption();
        opt1.setOptionType("S");
        opt1.setContent("是");

        PoVoteOption opt2 = new PoVoteOption();
        opt2.setContent("必然是");
        opt2.setOptionType("S");

        PoVoteOption que2 = new PoVoteOption();
        que2.setOptionType("Q");
        que2.setContent("questions");

        PoVoteOption opt21 = new PoVoteOption();
        opt21.setOptionType("M");
        opt21.setContent("answer1");

        PoVoteOption opt22 = new PoVoteOption();
        opt22.setOptionType("M");
        opt22.setContent("answer2");

        voteOptions.add(que1);
        voteOptions.add(opt1);
        voteOptions.add(opt2);
        voteOptions.add(que2);
        voteOptions.add(opt21);
        voteOptions.add(opt22);

        launchVoteService.launchVote(voteInfo,voteOptions);
    }
}
