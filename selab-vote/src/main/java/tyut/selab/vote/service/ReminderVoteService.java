package tyut.selab.vote.service;

import org.springframework.stereotype.Service;
import tyut.selab.vote.domain.po.VoteReminder;

import java.util.List;

/**
 * @className: ReminderVoteService
 * @author: lizhichao
 * @description: TODO
 * @date: 2023/10/9 20:00
 * @version: 1.0
 */
@Service
public interface ReminderVoteService {


    /**
     * 查询投票消息
     * @return
     */
   List<VoteReminder> queryVoteReform();

    /**
     * 修改通知为已读
     */
   void modifyIsRead(Long informId);

    /**
     * 查询是否有新消息
     */
   Integer queryNews();





}
