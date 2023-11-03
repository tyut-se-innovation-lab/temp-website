package ruoyi_quartz.vote;

import com.ruoyi.RuoYiApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.VoteReminder;
import tyut.selab.vote.service.ReminderVoteService;

import java.util.List;

/**
 * @className: VoteReminderTest
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/11/1 12:17
 * @version: 1.0
 */
@Slf4j
@SpringBootTest(classes = RuoYiApplication.class)
public class VoteReminderTest {
    @Autowired
    ReminderVoteService reminderVoteService;
    @Test
    void queryNews(){
        List<VoteReminder> voteReminders = reminderVoteService.queryVoteReform();
        log.info(voteReminders.toString());
    }
}
