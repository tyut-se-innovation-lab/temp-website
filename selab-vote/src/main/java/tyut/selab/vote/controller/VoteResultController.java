package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.po.VoteUser;

import java.util.ArrayList;

/**
 * @className: VoteResultController
 * @author: lizhichao
 * @description: TODO 管理员查询投票结果
 * @date: 2023/10/11 19:52
 * @version: 1.0
 */
@RequestMapping("/vote/result")
@RestController
public class VoteResultController {

    /**
     * 查询投票细节
     * @param voteId
     * @return
     */
    @GetMapping("/showDetails/{voteId}")
    public AjaxResult showVoteDetails(@PathVariable Long voteId){
        return null;
    }


}
