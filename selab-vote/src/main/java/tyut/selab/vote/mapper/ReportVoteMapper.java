/**
 * Copyright (C), 2000-2023, XXX有限公司
 * FileName: ReportVoteMapper
 * Author: 22932
 * Date: 2023/10/11 18:53:02
 * Description:
 * <p>
 * History:
 * <author> 作者姓名
 * <time> 修改时间
 * <version> 版本号
 * <desc> 版本描述
 */
package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteReport;

import java.util.List;

/**
 * @ClassName: ReportVoteMapper
 * @Description: java类描述
 * @Author: 22932
 * @Date: 2023/10/11 18:53:02
 */
public interface ReportVoteMapper {
    /**
     * 提交举报信息
     * @param voteReport
     * @return
     */
    Integer submitReportVote(@Param("voteReport") VoteReport voteReport);

    /**
     * 查看举报信息
     * @param voteId
     * @return
     */
    List<VoteReport> viewReportVote(@Param("voteId") Long voteId);

    /**
     * 查询举报次数
     * @param voteId
     * @return
     */
    Integer queryReportCount(@Param("voteId") Long voteId);
}
