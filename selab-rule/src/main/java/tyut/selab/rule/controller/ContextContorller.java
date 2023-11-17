package tyut.selab.rule.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.rule.service.ContextService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Api("文件文档类")
@RestController
@RequestMapping("/file")
public class ContextContorller {
    @Autowired
    ContextService contextService;

    @ApiOperation("获取文件流展示到前端下载") // @PreAuthorize("@ss.hasPermi('rule:editor')")
    @GetMapping("/deliver")
    public AjaxResult deliverFile(HttpServletResponse res) throws IOException {
        contextService.showfiles(res);
        return AjaxResult.success();
    }

    @ApiOperation("前端编辑后后端获取流写入文件")
    @PreAuthorize("@ss.hasAnyRoles('admin')")
    @PostMapping("/edit")
    public AjaxResult editfile(HttpServletRequest req) throws IOException {
        contextService.editdfile(req);
        return AjaxResult.success();
    }
}

