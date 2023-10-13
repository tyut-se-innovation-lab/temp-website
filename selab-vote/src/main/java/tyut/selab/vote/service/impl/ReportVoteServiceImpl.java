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

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteReport;
import tyut.selab.vote.service.ReportVoteService;

import java.util.List;

/**
 * @ClassName: ReportVoteServiceImpl
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/9 13:38:21
 */
@Service
public class ReportVoteServiceImpl implements ReportVoteService {
    @Override
    public void submitReportVote(VoteReport voteReport) {

    }

    @Override
    public List<VoteReport> viewReportVote(Long voteId) {
        return null;
    }
}

