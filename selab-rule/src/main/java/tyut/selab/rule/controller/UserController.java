package tyut.selab.rule.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.domain.entity.Operation;
import tyut.selab.rule.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/rule")
@Slf4j
public class UserController {
    //查看用户的当前分数和与该用户相关的操作
    @Autowired
    private UserService userService;
    @Autowired
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
     * 查询用户当天的所有相关日志
     * 接收传来的日期，查询该日期的所有日志
     * @return
     */
    // 开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @GetMapping("/dayLog")
    @ApiOperation("查询用户当天的所有相关日志")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult getScoreChangeLogForDay(HttpServletRequest request, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, @RequestParam("startTime") Date startTime) {
        LoginUser user = tokenService.getLoginUser(request);
        List<Operation> operationList = userService.getLogForDay(user.getUserId(), pageNum, pageSize, startTime);
        return AjaxResult.success(operationList);
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
