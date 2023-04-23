package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.ILaunchVoteService;
import tyut.selab.vote.tools.impl.VoPoConverterTool;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * @author Big_bai on 2022/12/2
 */
@RestController
@RequestMapping("/selab/vote")
public class LunchVoteController {

    @Autowired
    private ILaunchVoteService launchVoteService;

    @PreAuthorize("@ss.hasPermi('vote:initiate')")
    @PostMapping("/initiate")
    public AjaxResult lunchVote(@RequestBody Questionnaire questionnaire){
        launchVoteService.launchVote(questionnaire,getUserId());
        return new AjaxResult(200,"投票正在上传,请稍后(⌓‿⌓)");
    }
}
