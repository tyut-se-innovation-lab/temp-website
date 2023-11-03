import com.ruoyi.RuoYiApplication;
import com.ruoyi.system.mapper.SysConfigMapper;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysMenuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.poi.openxml4j.opc.internal.unmarshallers.PackagePropertiesUnmarshaller;
import org.apache.xmlbeans.impl.xb.xsdschema.impl.PublicImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tyut.selab.schedule.domain.po.Schedule;
import tyut.selab.schedule.enums.Period;
import tyut.selab.schedule.enums.Status;
import tyut.selab.schedule.enums.Week;
import tyut.selab.schedule.enums.WeekNo;
import tyut.selab.schedule.mapper.IDisplayScheduleMapper;
import tyut.selab.schedule.mapper.IUploadScheduleMapper;
import tyut.selab.schedule.service.IDisplayScheduleService;
import tyut.selab.schedule.service.IUploadScheduleService;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteOpinionContent;
import tyut.selab.vote.domain.po.VoteRange;
import tyut.selab.vote.mapper.QueryVoteMapper;
import tyut.selab.vote.mapper.VoteResultMapper;
import tyut.selab.vote.service.IShowVoteResultService;

import java.util.*;

/**
 * @author Big_bai on 2022/10/4
 */
@Slf4j
@SpringBootTest(classes = RuoYiApplication.class)
public class UploadScheduleMapperTest {
    @Autowired
    QueryVoteMapper queryVoteMapper;
    @Autowired
    IShowVoteResultService showVoteResultService;
    @Autowired
    VoteResultMapper voteResultMapper;

    @Test
    public void shouldShowAllSchedule(@Autowired IUploadScheduleService service){
        service.crawlScheduleIdentifiedByCookie(1L,"uWVLVHT_G0ZQWup1yqT0qTP_qgmDQs-VdYdZjl9F82UUTZpKPBJZJLdYC8r4KA8a7UYf-9K5Ap6rnFIqvI-d3lSTd8ng_5vnyZWohZrGmqc1","nwqvn5ri0qrnxylsrqyghy2j");
    }

    @Test
    public void shouldSorted(@Autowired IDisplayScheduleService service){
        service.selectScheduleList(1L);
    }
    @Test
    void testGetVoteInfoByVoteId(){
        VoteInfo voteInfoByVoteId = queryVoteMapper.getVoteInfoByVoteId(1L);
        log.info(voteInfoByVoteId.toString());
    }
    @Test
    void testQueryForParams(){
        List<VoteRange> voteRanges = queryVoteMapper.queryForParams(1, 1, 1L);
        System.out.println(voteRanges);
    }
    @Test
    void testShowVoteInfo(){
        VoteInfo voteResultDetailsByVoteId = showVoteResultService.getVoteResultDetailsByVoteId(1L);
        System.out.println(voteResultDetailsByVoteId);
    }
    @Test
    void testGetVoteContent(){
        List<VoteOpinionContent> optionContent = voteResultMapper.getOptionContent(1L,1L);
        log.info(optionContent.toString());
    }
    @Test
    void testGetVoteInfo(){
        VoteInfo voteInfoByVoteId = showVoteResultService.getVoteInfoByVoteId(1L);
        log.info(voteInfoByVoteId.toString());
    }

}
