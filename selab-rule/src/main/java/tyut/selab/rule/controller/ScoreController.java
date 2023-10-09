package tyut.selab.rule.controller;

import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.service.ScoreService;

/**
 * 分数管理
 */
@RestController
@RequestMapping("/rule/score")
@Api("分数增减")
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    /**
     * 分数增减
     *
     * @param score
     * @return
     */
    @PutMapping
    @ApiOperation("分数增减")
    public R addOrReduceScore(Integer score, Long userId) {
        //加分减分操作
        scoreService.addOrReduceScore(score, userId);
        //设置默认弹窗弹出
        //该用户需要弹出提示
        scoreService.setUserPop(1);
        //该操作需要弹出提示
        scoreService.setOperationPop(1);
        return R.ok();
    }
}
