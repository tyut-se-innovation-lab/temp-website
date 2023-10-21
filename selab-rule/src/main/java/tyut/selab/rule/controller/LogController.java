package tyut.selab.rule.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.domain.VO.OperationVO;
import tyut.selab.rule.mapper.RuleLogMapper;
import tyut.selab.rule.service.LogService;

import java.util.List;

@Api("日志管理")
@RestController
@RequestMapping("/rule/log")
@Slf4j
public class LogController {

    @Autowired
    private LogService logService;

    //删除日志
    @ApiOperation("根据日志ID删除日志")
    @DeleteMapping("/deleteLog/{logId}")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult deleteLog(@PathVariable String logId){
        log.info(logId);
        logService.deleteLog(Long.valueOf(logId));
        return AjaxResult.success();
    }

    //查出库中所有日志
    @ApiOperation("查出库中所有日志")
    @GetMapping("/selectAllLog")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    public AjaxResult selectAllLog(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<OperationVO> operationVOS = logService.selectAllLog();
        PageInfo<OperationVO> pageInfo = new PageInfo<>(operationVOS);
        return AjaxResult.success(pageInfo.getList());
    }
}
