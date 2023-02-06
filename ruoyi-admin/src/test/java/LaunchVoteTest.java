import ch.qos.logback.classic.pattern.SyslogStartConverter;
import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.mapper.InsertInfoDBMapper;
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

    @Autowired
    InsertInfoDBMapper insertInfoDBMapper;

    @Test
    public void launchVoteTest() {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setContent("傻儿子吉吉");
        questionnaire.setDeadline(new Date());
        questionnaire.setCreatTime(new Date());
        questionnaire.setTitle("嘿嘿嘿");
        setVoteQue(questionnaire);
        launchVoteService.launchVote(questionnaire,3L);
    }
    private void setVoteQue(Questionnaire questionnaire){
        List<VoteQue> voteQues = new ArrayList<>();

        VoteQue v1 = new VoteQue();
        v1.setType("Q");
        v1.setQueContent("我是不是吉吉的父亲");
        setVoteOpt(v1);
        voteQues.add(v1);

        questionnaire.setVoteQues(voteQues);

    }
    private void setVoteOpt(VoteQue voteQue){
        List<VoteOption> options = new ArrayList<>();

        VoteOption v1 = new VoteOption();
        v1.setContent("是的");
        v1.setType("S");
        options.add(v1);

        VoteOption v2 = new VoteOption();
        v2.setContent("必须");
        v2.setType("S");
        options.add(v2);

        voteQue.setOptions(options);
    }

    @Test
    public void test(){
//        List<PoVoteOption> l = new ArrayList<>();
//        PoVoteOption p1 = new PoVoteOption();
//        p1.setVoteId(1L);
//        p1.setParentId(-1L);
//        p1.setContent("xxx");
//        p1.setOptionType("Q");
//        l.add(p1);
//        insertInfoDBMapper.writeVoteOptionToDB(l);
        VoteInfo voteInfo = new VoteInfo();
        voteInfo.setWeight(4L);
        voteInfo.setStatus("1");
        voteInfo.setTitle("xxxx");
        voteInfo.setUserId("4");
        voteInfo.setContent("dsadasdsadas");
        voteInfo.setDeadline(new Date());
        voteInfo.setCreateTime(new Date());
        insertInfoDBMapper.writeVoteInfoToDB(voteInfo);
        System.err.println(voteInfo.getId());
    }
}
