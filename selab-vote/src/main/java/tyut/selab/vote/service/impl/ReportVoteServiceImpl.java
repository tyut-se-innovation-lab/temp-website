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
import tyut.selab.vote.domain.po.VoteReport;
import tyut.selab.vote.enums.VoteStatus;
import tyut.selab.vote.mapper.DealVoteMapper;
import tyut.selab.vote.mapper.ReportVoteMapper;
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
    private DealVoteMapper dealVoteMapper;
    @Autowired
    private ReportVoteMapper reportVoteMapper;


    @Override
    public Integer submitReportVote(VoteReport voteReport) {
        return null;
    }

    @Override
    public List<VoteReport> viewReportVote(Long voteId) {
        return null;
    }
}
