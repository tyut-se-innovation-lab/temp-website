package tyut.selab.vote.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 展示历史投票信息
 * @author Big-Bai
 **/
@RestController
@RequestMapping("/selab/vote/join")
public class ShowVoteController {

    /**
     * 返回历史投票信息粗略列表
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vote:history')")
    @GetMapping("/show")
    public AjaxResult getAllVoteRoughly(){
        return null;
    }

    /**
     * 根据投票id返回详细信息
     * @return
     */
    @PreAuthorize("@ss.hasPermi('vote:history')")
    @GetMapping("/allInfo")
    public AjaxResult getVoteScrutiny(){
        return null;
    }
}
