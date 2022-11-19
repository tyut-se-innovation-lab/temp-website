package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.IStartVoteService;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * @author Big_bai on 2022/11/19
 */
@RestController
@RequestMapping("/selab/vote")
public class SelabVote {
    @Autowired
    IStartVoteService startVoteService;
    @PreAuthorize("@ss.hasPermi('vote:initiate')")
    @PostMapping("/start")
    public AjaxResult startVote(@RequestBody Questionnaire questionnaire){
        startVoteService.initAVote(questionnaire,getUserId());
        return AjaxResult.success("正在上传中，请稍后~");
    }
}
