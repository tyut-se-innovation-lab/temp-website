package tyut.selab.rule.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.domain.VO.LogVO;
import tyut.selab.rule.service.LogService;

@Api("日志管理")
@RestController
@RequestMapping("/rule/log")
@Slf4j
public class LogController {

    @Autowired
    private LogService logService;


    //删除日志
    @ApiOperation("根据日志ID删除日志")
    @DeleteMapping("/deleteLog")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult deleteLog(@RequestParam Long userId, @RequestParam Long logId) {
        logService.deleteLog(userId, logId);
        return AjaxResult.success();
    }

    //查出库中所有日志
    @ApiOperation("查出库中所有日志")
    @GetMapping("/selectAllLog")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult selectAllLog(@RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        LogVO logVO = logService.selectAllLog(pageNum, pageSize);

        return AjaxResult.success(logVO);
    }
}
