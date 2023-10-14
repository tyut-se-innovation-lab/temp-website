package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.domain.po.VoteUser;

import java.util.ArrayList;

/**
 * @className: VotePeopleController
 * @author: lizhichao
 * @description: TODO 查看投票参与者
 * @date: 2023/10/3 22:36
 * @version: 1.0
 */
@RequestMapping("/vote")
@RestController
public class VotePeopleController {

    @PostMapping("/showPeople")
    public AjaxResult showAllVoter(){
        return AjaxResult.success(new ArrayList<VoteUser>());
    }

}
