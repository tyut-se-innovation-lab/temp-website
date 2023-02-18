import com.ruoyi.RuoYiApplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.PoVoteOption;
import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.domain.po.WeightPo;
import tyut.selab.vote.mapper.FindInfoDBMapper;
import tyut.selab.vote.service.impl.WeightControlService;
import tyut.selab.vote.tools.AnonymousControl;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

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
    @Test
    public void esByUserIdAndOptionIdTest1(@Autowired FindInfoDBMapper findInfoDBMapper){
        List<VoteResult> join = findInfoDBMapper.getOptionId("3");
        join.forEach(System.out::println);
//        poVoteOptions.forEach(System.out::println);
    }
    @Test
    public void weightTest(@Autowired WeightControlService service){
        WeightPo nowVoteWeight = service.getNowVoteWeight();
        int weightByUserId = service.getWeightByUserId("2021001111");
        String encrypt = AnonymousControl.encrypt("2021001111");
        String encrypt1 = AnonymousControl.encrypt("2021001112");
        System.out.println(encrypt);
        System.out.println(encrypt1);
        String decrypt = AnonymousControl.decrypt("p16JIpvG1uw8SBU1XfUHdK/bi4vddvMEoDj+PboHe77X/jGgwGjG73B4Frll9wQyaUBrUe+0H3VKyrMWmrjWQH7HE4wCbFNEwcqdEO16kdnBX/NyYmEiVCYc0hMkqc0fiz0Xo94P1n2PiB8WK+KdXtvL6x8iJ5vlwGFCrHoLjcs=");
        System.out.println(decrypt);
        System.out.println(nowVoteWeight);
    }

}
