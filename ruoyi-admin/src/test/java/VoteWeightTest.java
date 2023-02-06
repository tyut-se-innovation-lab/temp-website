import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.WeightPo;
import tyut.selab.vote.domain.vo.Weight;
import tyut.selab.vote.service.IWeightControlService;

/**
 * @author Big_bai on 2022/11/22
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class VoteWeightTest {
    @Autowired
    IWeightControlService weightControlService;

    @Test
    public void getWeightByIdTest(){
        int i = weightControlService.getWeightByUserId("1");
        System.err.println(i);
    }
    @Test
    public void setGetWeightTest(){
        WeightPo w = new WeightPo(new Weight(5,2,2,2,2,2,2));
        weightControlService.setVoteWeight(w);
        w = weightControlService.getNowVoteWeight();
        System.err.println(1+w.toString());
        //        WeightPo w = new WeightPo();
//        w.setVoteManager(1);
//        w.setDEVLeader(1);
//        w.setCSLeader(1);
//        w.setDEVManager(1);
//        w.setCSManager(1);
//        w.setDEVMember(1);
//        w.setCSMember(1);
//        weightControlService.setVoteWeight(w);
//        w = weightControlService.getNowVoteWeight();
//        System.out.println(1+w.toString());
//
//        w.setVoteManager(1);
//        w.setDEVLeader(2);
//        w.setCSLeader(2);
//        w.setDEVManager(1);
//        w.setCSManager(1);
//        w.setDEVMember(1);
//        w.setCSMember(1);
//        weightControlService.setVoteWeight(w);
//        w = weightControlService.getNowVoteWeight();
//        System.out.println(2+w.toString());
//
//
//        w.setVoteManager(1);
//        w.setDEVLeader(2);
//        w.setCSLeader(2);
//        w.setDEVMember(1);
//        w.setCSMember(1);
//        weightControlService.setVoteWeight(w);
//        w = weightControlService.getNowVoteWeight();
//        System.out.println(3+w.toString());
//
//        w.setVoteManager(1);
//        w.setDEVLeader(1);
//        w.setCSLeader(1);
//        w.setDEVManager(1);
//        w.setCSManager(1);
//        w.setDEVMember(1);
//        w.setCSMember(1);
//        weightControlService.setVoteWeight(w);
//        w = weightControlService.getNowVoteWeight();
//        System.out.println(4+w.toString());
    }
}
