package tyut.selab.vote.service;

import org.springframework.stereotype.Service;

/**
 * @className: ReminderVoteService
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/9 20:00
 * @version: 1.0
 */
@Service
public interface ReminderVoteService {

    Integer  voteReportReminder();

    Integer voteWithdrawReminder();



}
