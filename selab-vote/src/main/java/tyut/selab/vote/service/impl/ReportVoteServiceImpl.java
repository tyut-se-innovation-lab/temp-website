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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteReport;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.exception.*;
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
    public void submitReportVote(VoteReport voteReport) throws VoteOverTimeException, VoteFreezedException, ReportRepeatException, VoteWithdrawnException {

        int freezeCount = 10;
        //Long userId = SecurityUtils.getUserId();
        voteReport.setUser_id(73L);

        if (!TimeDealTool.judgeVoteFinish(voteInfoMapper.queryVoteDeadTime(voteReport.getVoteId())))
            throw new VoteOverTimeException("该投票已结束");

        if (voteInfoMapper.getVoteStatus(voteReport.getVoteId()).getStatus() == VoteStatus.CLOSED)
            throw new VoteCloseException("该投票已被关闭");

        if (voteInfoMapper.getVoteStatus(voteReport.getVoteId()).getStatus() == VoteStatus.WITHDRAW)
            throw new VoteWithdrawnException("该投票已被撤回");

        if (reportVoteMapper.queryReportCount(voteReport.getVoteId()) >= freezeCount)
            throw new VoteFreezedException("该投票已被冻结");

        reportVoteMapper.submitReportVote(voteReport);
        if (reportVoteMapper.queryReportCount(voteReport.getVoteId()) >= freezeCount)
            voteInfoMapper.updateVoteStatus(voteReport.getVoteId(), VoteStatus.FREEZE);
    }

    @Override
    public List<VoteReport> viewReportVote(Long voteId) {
        return reportVoteMapper.viewReportVote(voteId);
    }
}
