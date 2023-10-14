/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: ReportVoteServiceImpl
 * Author: 22932
 * Date: 2023/10/9 13:38:21
 * Description:
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteInfo;
import tyut.selab.vote.domain.po.VoteReport;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.mapper.ReportVoteMapper;
import tyut.selab.vote.mapper.VoteInfoMapper;
import tyut.selab.vote.service.ReportVoteService;
import tyut.selab.vote.tools.TimeDealTool;

import java.util.List;

/**
 * @ClassName: ReportVoteServiceImpl
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/9 13:38:21
 */
@Service
public class ReportVoteServiceImpl implements ReportVoteService {

    @Autowired
    private VoteInfoMapper voteInfoMapper;
    @Autowired
    private ReportVoteMapper reportVoteMapper;

    @Override
    public Integer submitReportVote(VoteReport voteReport) {
        //举报次数达到，则冻结
        int freezeCount = 10;
        if(TimeDealTool.judgeVoteFinish(voteInfoMapper.queryVoteDeadTime(voteReport.getVoteId()))){
            //未到截止时间,可以提交举报信息
            //Long userId = SecurityUtils.getUserId();
            //voteReport.setUser_id(userId);
            if(reportVoteMapper.queryReportCount(voteReport.getVoteId()) >= freezeCount) {
                return 2;
            }
            reportVoteMapper.submitReportVote(voteReport);
            if(reportVoteMapper.queryReportCount(voteReport.getVoteId()) >= freezeCount) {
                voteInfoMapper.updateVoteStatus(voteReport.getVoteId(),VoteStatus.FREEZE);
            }
            return 1;
        }else{
            //此刻超出截止时间
            return 0;
        }

    }

    @Override
    public List<VoteReport> viewReportVote(Long voteId) {
        return reportVoteMapper.viewReportVote(voteId);
    }


}
