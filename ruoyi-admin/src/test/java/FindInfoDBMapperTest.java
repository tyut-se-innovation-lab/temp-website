import com.ruoyi.RuoYiApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IWithdrowVoteService;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import java.util.List;


@SpringBootTest(classes = RuoYiApplication.class)
public class FindInfoDBMapperTest {
    @Test
    public void FindInfoDBMapper(@Autowired IWithdrowVoteService iWithdrowVoteService) throws Exception {
        List<VoteInfo> voteInfoById = iWithdrowVoteService.delectVoteInfoById(1);
    }
    @Test
    public void esByUserIdAndOptionIdTest(@Autowired FindInfoDBMapper findInfoDBMapper){
        List<PoVoteOption> voteOptions = findInfoDBMapper.getVoteOptionByVoteId(1L);
        List<VoteResult> resByUserIdAndOptionId = findInfoDBMapper.getResByUserIdAndOptionId("2021001111", voteOptions);
        resByUserIdAndOptionId.forEach(System.out::println);
    }
    @Test
    public void isSelectTest(@Autowired FindInfoDBMapper mapper){
        List<PoVoteOption> voteOption = mapper.getVoteOptionByVoteId(1L);
        List<VoteResult> resByUserIdAndOptionId = mapper.getResByUserIdAndOptionId("2021001111", voteOption);
        VoPoConverterTool tool = new VoPoConverterTool();
        System.out.println(voteOption.get(0));
        resByUserIdAndOptionId.forEach(System.out::println);
        System.out.println(tool.isSelect(voteOption.get(0),resByUserIdAndOptionId));
    }

}
