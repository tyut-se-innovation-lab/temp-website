package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;

import lombok.Data;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.DTO.VoteReminderDTO;

import java.util.ArrayList;

/**
 * @className: ReminderVoteController
 * @author: lizhichao
 * @date: 2023/10/3 23:02
 * @description: TODO 投票消息提醒
 * @version: 1.0
 */
@RestController
@RequestMapping("/vote")
public class ReminderVoteController {

    /**
     * 获取投票模块消息
     * 
     * @return
     */
    @GetMapping("/reminder")
    public AjaxResult getVoteNews() {
        return AjaxResult.success(new ArrayList<VoteReminderDTO>());
    }

}
