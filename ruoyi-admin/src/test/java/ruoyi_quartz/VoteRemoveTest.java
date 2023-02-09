package ruoyi_quartz;

import com.ruoyi.RuoYiApplication;
import com.ruoyi.quartz.task.VoteTask;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.service.IRemoveWasteVoteRegularly;

@SpringBootTest(classes = RuoYiApplication.class)
public class VoteRemoveTest {
    @Autowired
    VoteTask voteTask;
    @Test
    public void voteRemove(){
        voteTask.removeWasteVoteRegularly();
    }
}
