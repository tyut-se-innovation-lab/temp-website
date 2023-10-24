package tyut.selab.vote.mapper;

import org.apache.ibatis.annotations.Param;
import tyut.selab.vote.domain.po.VoteReminder;

import java.util.List;


public interface ReminderVoteMapepr {

    /**
     *  写入投票消息
     * @Param voteReminderDTOS
     * @return
     */
    Integer insertVoteReminder(@Param("voteReminders") List<VoteReminder> voteReminders);

    /**
     *  查询举报通知
     * @param userId
     * @return
     */
    List<VoteReminder> getReportReminder(@Param("userId") Long userId);

    /**
     *  修改通知为已读
     * @param informId
     * @param userId
     * @return
     */
   Integer updateReminderIsRead(@Param("informId") Long informId,@Param("userId") Long userId);

    /**
     *  查询其他通知
     * @param userId
     * @return
     */
   List<VoteReminder> getVoteReminder(@Param("userId") Long userId);


   Integer getNewsNum(@Param("userId") Long userId);
}
