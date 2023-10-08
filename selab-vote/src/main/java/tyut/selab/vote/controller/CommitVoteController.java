package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.vote.domain.vo.Questionnaire;
import tyut.selab.vote.domain.vo.VoteOption;
import tyut.selab.vote.domain.vo.VoteQue;
import tyut.selab.vote.service.ICommitVoteService;

import java.util.List;

import static com.ruoyi.common.utils.SecurityUtils.getUserId;

/**
 * 上传投票
 *
 * @author Big-Bai
 **/
@RestController
@RequestMapping("/selab/vote/join")
public class CommitVoteController {
    @Autowired
    private ICommitVoteService commitVoteService;

    /**
     * 获取可以参与的投票的粗略信息
     *
     * @return
     */
    @PreAuthorize("@ss.hasAnyPermi('vote:join')")
    @GetMapping("/list")
    public AjaxResult getVoteListPermitted() {
        return AjaxResult.success(commitVoteService.listAllowed(getUserId()));
    }

    /**
     * 参与投票
     *
     * @return
     */
    @PreAuthorize("@ss.hasAnyPermi('vote:join')")
    @PostMapping("")
    public AjaxResult commitVote(@RequestBody Questionnaire questionnaire) {


        //飞哥要求第一次投票使用必须要选四个多选项，QAQ
        List<VoteQue> voteQues = questionnaire.getVoteQues();
        int i = 0;
        for (VoteQue vq : voteQues) {
            if ("M".equals(vq.getType())) {
                i = 0;
                for (VoteOption vp : vq.getOptions()) {
                    if (vp.getIsSelect() != null && vp.getIsSelect()) {
                        i++;
                    }
                }
                if (i != 4) {
                    throw new RuntimeException("请务必选择四个多选选项");
                }
            }
        }


        commitVoteService.recordSelection(questionnaire, getUserId());
        return AjaxResult.success("上传成功");
    }
}
