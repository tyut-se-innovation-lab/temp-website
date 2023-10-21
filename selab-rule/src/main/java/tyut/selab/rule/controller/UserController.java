package tyut.selab.rule.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.service.UserService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/rule")
@Slf4j
public class UserController {
    //查看用户的当前分数和与该用户相关的操作
    @Autowired
    private UserService userService;
    private TokenService tokenService;

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
    @GetMapping("/day")
    @ApiOperation("查询用户当天的增减分情况")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult getScoreChangeForDay(HttpServletRequest request) {
        LoginUser user = tokenService.getLoginUser(request);
        Integer scoreChange = userService.getScoreChangeForDay(user.getUserId());
        Integer score = scoreChange;
        String s = scoreChange.toString();
        log.info(s);
        return AjaxResult.success(score);
    }

    /**
     * 查询用户当月的增减分情况
     *
     * @return
     */
    @GetMapping("/month")
    @ApiOperation("查询用户当月的增减分情况")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult getScoreChangeForMonth(HttpServletRequest request) {
        LoginUser user = tokenService.getLoginUser(request);
        Integer scoreChange = userService.getScoreChangeForMonth(user.getUserId());
        return AjaxResult.success(scoreChange);
    }
}
