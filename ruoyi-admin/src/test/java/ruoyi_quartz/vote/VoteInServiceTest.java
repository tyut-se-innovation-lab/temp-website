package ruoyi_quartz.vote;

import com.ruoyi.RuoYiApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.mapper.QueryVoteMapper;

/**
 * @className: VoteInServiceTest
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/11/1 11:45
 * @version: 1.0
 */
@Slf4j
@SpringBootTest(classes = RuoYiApplication.class)
public class VoteInServiceTest {
    @Autowired
    QueryVoteMapper queryVoteMapper;
    @Test
    void testGetVoteInfo(){
        VoteInfo voteInfoByVoteId = queryVoteMapper.getVoteInfoByVoteId(1L);
        log.info(voteInfoByVoteId.toString());
    }
}
