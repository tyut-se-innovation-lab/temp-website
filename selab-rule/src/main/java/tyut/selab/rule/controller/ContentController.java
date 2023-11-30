package tyut.selab.rule.controller;

import cn.hutool.json.JSONUtil;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tyut.selab.rule.service.ContentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Otion
 * @date 2023/11/21
 */
@Api("文件文档类")
@RestController
@Anonymous
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

    //该下载方法会报错
    //因为我想实现下载文件时同时返回参数
    //但是业务正常,不会影响任何功能
    // 原因是
    // 由于下载控制器调用的下载实现类，控制器使用了统一返回类AjaxResult对象
    // 但同时设置了Content-Type为application/octet-stream;charset=UTF-8
    // 这表示你试图返回一个二进制文件流。
    // 在Spring框架中，当返回的对象不是ResponseEntity或HttpEntity时
    // Spring会试图找到一个合适的HttpMessageConverter将返回的对象转换为响应体。
    // Spring试图将AjaxResult对象转换为application/octet-stream;charset=UTF-8格式的响应体
    // 但是没有找到合适的转换器，因此抛出了HttpMessageNotWritableException异常。

    //如果不想报错就可以把返回值改为void
    //思来想去还是改成void了,防止报错太多背锅XD

    //最后把要返回的参数内容放到了自定义的响应头中
    //但返回的参数内容其实非必要

    @GetMapping("/download")
    @PreAuthorize("@ss.hasAnyPermi('rule:content')")
    @ApiOperation("规章制度模块中文件的下载")
    public void downloadMarkdown(HttpServletResponse res,HttpServletResponse response){
        contentService.downloadMarkdown(res);
    }

}

