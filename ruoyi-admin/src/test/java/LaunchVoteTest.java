import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.service.ILaunchVoteService;
import tyut.selab.vote.tools.GetSysTime;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

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



        VoteOption v = new VoteOption();
        VoteOption v1 = new VoteOption();
        List<VoteOption> voteOptions = new ArrayList<>();
        v.setType("M");
        v.setContent("M");
        v1.setType("M");
        v1.setContent("M");
        voteOptions.add(v);
        voteOptions.add(v1);

        List<VoteQue> voteQues = new ArrayList<>();
        VoteQue vq = new VoteQue();
        vq.setOptions(voteOptions);
        vq.setType("Q");
        vq.setQueContent("红红火火恍恍惚惚");
        voteQues.add(vq);

        Questionnaire  q = new Questionnaire();
        q.setContent("嘿嘿嘿");
        q.setDeadline(GetSysTime.getNow());
        q.setTitle("傻儿子吉吉嘿嘿嘿嘿嘿嘿嘿嘿");
        q.setVoteQues(voteQues);

        launchVoteService.launchVote(VoPoConverterTool.voToVoteInfo(q,10L),VoPoConverterTool.voToPoVoteOptions(q));
    }
}
