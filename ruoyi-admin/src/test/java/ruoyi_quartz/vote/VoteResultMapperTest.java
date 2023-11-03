package ruoyi_quartz.vote;

import com.ruoyi.RuoYiApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.mapper.VoteResultMapper;

import java.util.List;

/**
 * @className: VoteResultMapperTest
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/11/1 16:58
 * @version: 1.0
 */
@Slf4j
@SpringBootTest(classes = RuoYiApplication.class)
public class VoteResultMapperTest {

    @Autowired
    VoteResultMapper voteResultMapper;
    @Test
    void testGetVoteResult(){
        List<VoteResult> voteResultByOptionIdAndVoteId = voteResultMapper.getVoteResultByOptionIdAndVoteId(1L, null, "0D8btHGdYgaIDTwGPJHT1w==");
        log.info(voteResultByOptionIdAndVoteId.toString());
    }

}
