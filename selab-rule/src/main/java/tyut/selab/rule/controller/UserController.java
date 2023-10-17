package tyut.selab.rule.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.service.UserService;

@RestController
@RequestMapping("/rule")
public class UserController {
    //查看用户的当前分数和与该用户相关的操作
    @Autowired
    private UserService userService;

    @PreAuthorize("@ss.hasAnyPermi('rule:announcement')")
    @GetMapping("/userInfo")
    public AjaxResult userInfo(@RequestParam("userId") Long userId) {
        return userService.userInfo(userId);
    }

    /**
     * 查询用户当天的增减分情况
     *
     * @return
     */
    @GetMapping("/day/{userId}")
    @ApiOperation("查询用户当天的增减分情况")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult getScoreChangeForDay(@PathVariable Long userId) {
        Integer scoreChange = userService.getScoreChangeForDay(userId);
        return AjaxResult.success(scoreChange);
    }

    /**
     * 查询用户当月的增减分情况
     *
     * @param userId
     * @return
     */
    @GetMapping("/month/{userId}")
    @ApiOperation("查询用户当月的增减分情况")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult getScoreChangeForMonth(@PathVariable Long userId) {
        Integer scoreChange = userService.getScoreChangeForMonth(userId);
        return AjaxResult.success(scoreChange);
    }
}
