package tyut.selab.vote.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteReminder;
import tyut.selab.vote.exception.VoteDataUnknownException;
import tyut.selab.vote.mapper.ReminderVoteMapepr;
import tyut.selab.vote.service.ReminderVoteService;

import java.util.List;

/**
 * @className: ReminderVoteServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/19 20:50
 * @version: 1.0
 */
@Service
public class ReminderVoteServiceImpl implements ReminderVoteService {

    @Autowired
    ReminderVoteMapepr reminderVoteMapepr;
    @Override
    public List<VoteReminder> queryVoteReform() {
//        Long userId = SecurityUtils.getUserId();
        Long userId = 1L;
        // 查询 所有通知
        List<VoteReminder> voteReminder = reminderVoteMapepr.getVoteReminder(userId);
//        Long roleId = SecurityUtils.getLoginUser().getUser().getRoleId();
        Long roleId = 100L;
        if(roleId <= 100){
            List<VoteReminder> reportReminder = reminderVoteMapepr.getReportReminder(userId);
           voteReminder.addAll(reportReminder);
        }
        return voteReminder;
    }

    @Override
    public void modifyIsRead(Long informId) {
        Long userId = SecurityUtils.getUserId();
        Integer rows = reminderVoteMapepr.updateReminderIsRead(informId, userId);
        if(rows != 1){
            throw new VoteDataUnknownException("数据库未知异常");
        }
    }

    @Override
    public Integer queryNews() {
        Long userId = SecurityUtils.getUserId();
        Integer newsNum = reminderVoteMapepr.getNewsNum(userId);
        return newsNum;
    }
}
