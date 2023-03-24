import com.ruoyi.RuoYiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.attendance.mapper.AttendanceMapper;

import tyut.selab.vote.domain.po.VoteResult;
import tyut.selab.vote.service.ICommitVoteService;


import java.util.ArrayList;
import java.util.Date;

import static com.ruoyi.common.utils.DateUtils.*;

/**
 * @Author: Gulu
 * @Date: 2022/11/15 14:48
 */
@SpringBootTest(classes = RuoYiApplication.class)
public class CommitVoteMapperTest {
@Test
public void commitVote(@Autowired ICommitVoteService service,@Autowired AttendanceMapper attendanceMapper){
    /*//添加投票结果测试
    VoteResult voteResult1 = new VoteResult();
    voteResult1.setVoteOptionId(758437L);
    voteResult1.setUserId("hgfjhg");
    voteResult1.setContent("hghjhfdgh");
    voteResult1.setCreateTime(new Date());
    VoteResult voteResult2 = new VoteResult();
    voteResult2.setVoteOptionId(7556457L);
    voteResult2.setUserId("jghdfjk");
    voteResult2.setContent("hh6786");
    voteResult2.setCreateTime(new Date());
    ArrayList<VoteResult> voteResults = new ArrayList<>();
    voteResults.add(voteResult1);
    voteResults.add(voteResult2);
    System.out.println(service.commitVoteResult(voteResults));
    //获取时间测试
    System.out.println(service.getDeadLineById(1L));*/
    //System.out.println(attendanceMapper.signIn("20", dateTime(YYYY_MM_DD_HH_MM_SS, getTime())));
    //System.out.println(attendanceMapper.couleSignOut("20"));
    System.out.println(attendanceMapper.signOut("20", dateTime(YYYY_MM_DD_HH_MM_SS, getTime()), 1));
}
}
