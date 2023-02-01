import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.core.domain.AjaxResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.IDisplayAllVoteService;
import tyut.selab.vote.service.IDisplayVoteResultService;
import tyut.selab.vote.service.IWithdrowVoteService;

import java.util.Iterator;
import java.util.List;

@SpringBootTest(classes = RuoYiApplication.class)
public class MyVoteTest {
    @Autowired
    IDisplayAllVoteService iDisplayAllVoteService;
    @Autowired
    private IWithdrowVoteService withdrowVoteService;
    @Autowired
    private IDisplayVoteResultService iDisplayVoteResultService;

    //用户自己的历史投票信息(粗略)
    @Test
    public void displayMyALLVote(){
        System.out.println("===========================================");
        System.out.println(iDisplayAllVoteService.displayMyJoinVote("1"));
        System.out.println(AjaxResult.success(iDisplayAllVoteService.displayMyJoinVote("1")));
        System.out.println("===========================================");
    }

    //查看我创建的投票列表（粗略）
    @Test
    public void myLaunchVote(){
        System.out.println("===========================================");
        System.out.println(iDisplayAllVoteService.displayMyStartVote("1"));
        System.out.println(AjaxResult.success(iDisplayAllVoteService.displayMyStartVote("1")));
        System.out.println("===========================================");
    }

    //对发起的投票进行撤回
    @Test
    public void deleteVote(){
        System.out.println("===========================================");
        System.out.println(withdrowVoteService.delectVoteInfoById(Long.valueOf("1")));
        System.out.println(withdrowVoteService.delectVoteInfoById(Long.valueOf("1")) == null ?
                AjaxResult.success("撤回成功") :
                AjaxResult.error("撤回失败"));
        System.out.println("===========================================");
    }

    @Test
    public void displayMyJoinVoteService() {
        List<Questionnaire> questionnaires = iDisplayAllVoteService.displayMyJoinVote("1");
        Iterator<Questionnaire> iterator = questionnaires.iterator();
        System.out.println("===========================================");
        while (iterator.hasNext()) {
            Questionnaire next = iterator.next();
            System.out.println(next);
        }
        System.out.println("===========================================");
    }

    /**
     * 提前结束vote
     */
    @Test
    public void finishVote(){
        System.out.println("===========================================");
        System.out.println(iDisplayAllVoteService.finishVote(String.valueOf(1)));
        System.out.println("===========================================");
    }

    /**
     * 显示用户自己的历史投票内容(详细)
     */
    @Test
    public void test(){
        System.out.println("===========================================");
        System.out.println(iDisplayVoteResultService.displayVoteResult(1L, "1"));
        System.out.println("===========================================");
    }

}
