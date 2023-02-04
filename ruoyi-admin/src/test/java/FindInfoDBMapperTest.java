import com.ruoyi.RuoYiApplication;
import com.ruoyi.system.service.ISysConfigService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.WeightPo;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.Weight;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.service.IWithdrowVoteService;
import tyut.selab.vote.service.impl.DisplayAllVoteImpl;
import tyut.selab.vote.service.impl.DisplayVoteResultServiceImpl;
import tyut.selab.vote.service.impl.WeightControlService;
import tyut.selab.vote.service.impl.WithdrowVoteServiceImpl;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import javax.validation.constraints.Size;
import java.util.List;


@SpringBootTest(classes = RuoYiApplication.class)
public class FindInfoDBMapperTest {
//    @Test
//    public void FindInfoDBMapper(@Autowired IWithdrowVoteService iWithdrowVoteService) throws Exception {
//        List<VoteInfo> voteInfoById = iWithdrowVoteService.findVoteInfoById(1);
//    }
    @Test
    public void esByUserIdAndOptionIdTest(@Autowired FindInfoDBMapper findInfoDBMapper){
        List<PoVoteOption> voteOptions = findInfoDBMapper.getVoteOptionByVoteId(1L);
        List<VoteResult> resByUserIdAndOptionId = findInfoDBMapper.getResByUserIdAndOptionId(2021001111L, voteOptions);
        resByUserIdAndOptionId.forEach(System.out::println);
    }
    @Test
    public void isSelectTest(@Autowired FindInfoDBMapper mapper){
        List<PoVoteOption> voteOption = mapper.getVoteOptionByVoteId(1L);
        List<VoteResult> resByUserIdAndOptionId = mapper.getResByUserIdAndOptionId(2021001111L, voteOption);
        VoPoConverterTool tool = new VoPoConverterTool();
        System.out.println(voteOption.get(0));
        resByUserIdAndOptionId.forEach(System.out::println);
        System.out.println(tool.isSelect(voteOption.get(0),resByUserIdAndOptionId));
    }
    @Test
    public void esByUserIdAndOptionIdTest1(@Autowired FindInfoDBMapper findInfoDBMapper){
        int i = findInfoDBMapper.theNumOfJoinVote(1L);
        System.out.println(i);
//        poVoteOptions.forEach(System.out::println);
    }
    @Test
    public void weightTest(@Autowired WeightControlService service){
        WeightPo nowVoteWeight = service.getNowVoteWeight();
        int weightByUserId = service.getWeightByUserId(2021001111L);
        System.out.println(nowVoteWeight);
    }

}
