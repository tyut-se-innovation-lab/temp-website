package tyut.selab.rule.controller;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tyut.selab.rule.service.ContentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author Otion
 * @date 2023/11/21
 */
@Api("文件文档类")
@RestController
@RequestMapping("/rule/file")
public class ContentController {
    @Autowired
    ContentService contentService;

    @PostMapping("/upload")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    @ApiOperation("规章制度模块中文件的上传")
    public AjaxResult uploadMarkdown(HttpServletRequest request, @RequestPart(value = "file") MultipartFile file){
        AjaxResult result = contentService.uploadMarkdown(request,file);
        return result;
    }

    @GetMapping("/download")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    @ApiOperation("规章制度模块中文件的下载")
    public void downloadMarkdown(HttpServletResponse res,HttpServletResponse response){
        contentService.downloadMarkdown(res);
        response.setStatus(200);
    }


}

