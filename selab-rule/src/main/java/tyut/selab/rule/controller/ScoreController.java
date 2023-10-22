package tyut.selab.rule.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.domain.DTO.ScoreRequestDTO;
import tyut.selab.rule.service.ScoreService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     * @param scoreRequestDTO
     * @return
     */
    @PutMapping
    @ApiOperation("分数增减操作")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult addOrReduceScore(HttpServletRequest request, @RequestBody ScoreRequestDTO scoreRequestDTO) {
        //加分减分操作
        scoreService.addOrReduceScore(request, scoreRequestDTO);
        //设置默认弹窗弹出
        //该用户需要弹出提示
        scoreService.setUserPop(1);
        //该操作需要弹出提示
        scoreService.setOperationPop(1);
        return AjaxResult.success();
    }

    /**
     * 查询对应分数及以上的用户
     *
     * @param scoreRequestDTO
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查询对应分数及以上的用户")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult list( @RequestParam Long scoreCheck, @RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUsers = scoreService.list(scoreCheck);
        PageInfo<SysUser> pageInfo = new PageInfo<>(sysUsers);
        return AjaxResult.success(pageInfo);
    }


}
