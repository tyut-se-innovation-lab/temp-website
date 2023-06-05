package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.vo.JoinQuestionnaire;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.service.IShowDetailedVoteListService;
import tyut.selab.vote.service.IShowRoughVoteListService;

import java.util.List;

/**
 * 展示历史投票信息
 * @author Big-Bai
 **/
@RestController
@RequestMapping("/selab/vote/join")
public class ShowVoteController {

    @Autowired
    IShowRoughVoteListService roughService;

    @Autowired
    IShowDetailedVoteListService detailedService;

    /**
     * 返回历史投票信息粗略列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vote:history')")
    @GetMapping("/show")
    public AjaxResult getAllVoteRoughly(){
        List<JoinQuestionnaire> questionnaireList = roughService.showAllVote();
        return AjaxResult.success("200",questionnaireList);
    }

    /**
     * 根据投票id返回详细信息
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vote:history')")
    @GetMapping("/allInfo/{id}")
    public AjaxResult getVoteScrutiny(@PathVariable("id") Long voteId){
        Questionnaire questionnaire = detailedService.showDetailedVote(voteId);
        if(questionnaire.isEmpty()){
            return AjaxResult.error("500","该投票不存在");
        }
        return AjaxResult.success("200",questionnaire);
    }
}
