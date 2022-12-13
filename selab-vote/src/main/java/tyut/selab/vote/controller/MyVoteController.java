package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.IDisplayAllVoteService;

import java.util.List;

@RestController
@RequestMapping("/selab/vote/mine")
public class MyVoteController {
    @Autowired
    private IDisplayAllVoteService iDisplayAllVoteService;

//    /**
//     * 查看所有投票列表
//     * @param userId 用户名 ：空也行，不碍事
//     * @return 返回投票列表
//     */
//    @GetMapping("/joined/list")
//    @PreAuthorize("@ss.hasPermi('vote:join')")
//    public AjaxResult displayMyALLVote(Long userId){
//        return AjaxResult.success(iDisplayAllVoteService.displayAllVote(userId.toString()));
//    }

    /**
     * 查看我参与的投票列表
     *
     * @param userId
     * @return 返回我参与的投票列表
     */
    @GetMapping("/joined/list")
    @PreAuthorize("@ss.hasPermi('vote:join')")
    public AjaxResult displayMyALLVote(@RequestBody Long userId) {
        return AjaxResult.success(iDisplayAllVoteService.displayAllVote(userId.toString()));
    }

    /**
     * 查看我创建的投票列表
     * @param userId 用户名
     * @return 返回我创建的投票列表
     */
    @GetMapping("/launched/list")
    @PreAuthorize("@ss.hasPermi('vote:join')")
    public AjaxResult myLaunchVote(@RequestBody Long userId){
        return AjaxResult.success(iDisplayAllVoteService.displayMyStartVote(userId.toString()));
    }


}
