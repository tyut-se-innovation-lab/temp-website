package tyut.selab.rule.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.json.JSONNull;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.framework.web.service.TokenService;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tyut.selab.rule.domain.PO.RuleContent;
import tyut.selab.rule.mapper.RuleContentMapper;
import tyut.selab.rule.service.ContentService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class ContentServiceImpl implements ContentService {
    @Value("${rule.basePath}")
    private String baseFileName;
    @Autowired
    private TokenService tokenService;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    @Autowired
    private RuleContentMapper ruleContentMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public AjaxResult uploadMarkdown(HttpServletRequest request, MultipartFile file) {
        if (file == null) {
            return AjaxResult.error("文件为空捏");
        }
        //拼接文件名
        //基础文件名+日期+UUID+后缀名
        String origin = file.getOriginalFilename();
        String timeName = simpleDateFormat.format(new Date());
        String parentPath = baseFileName + timeName;
        //文件转存
        File parent = new File(parentPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }
        String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(origin);
        File destination = new File(parentPath + fileName);
        String finalPath = parentPath + fileName;
        Long userId = tokenService.getLoginUser(request).getUserId();
        RuleContent oldContent = ruleContentMapper.selectByPrimaryKey(1L);
        if (oldContent == null) {
            RuleContent newContent = new RuleContent();
            newContent.setId(1);
            newContent.setTextAddress(finalPath);
            newContent.setCreateTime(new Date());
            newContent.setCreateUser(userId);
            ruleContentMapper.insert(newContent);
        } else {
            oldContent.setTextAddress(finalPath);
            oldContent.setUpdateTime(new Date());
            oldContent.setUpdateUser(userId);
            ruleContentMapper.updateByPrimaryKey(oldContent);
        }

        try {
            file.transferTo(destination);
            return AjaxResult.success("文件上传成功捏");
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("网络繁忙,请稍后再试 XD");
        }
    }

    @Override
    public void downloadMarkdown(HttpServletResponse response) {
        //从库中拿到markdown文件地址
        RuleContent originFile = ruleContentMapper.selectByPrimaryKey(1L);
        String originFilePath = "";
        Resource resource = null;
        //使用相对路径拿到保底文件
        if (originFile == null) {
            resource = resourceLoader.getResource("classpath:rule/2023-规章制度.md");
        } else {
            originFilePath = originFile.getTextAddress();
        }

        //设置响应头
        response.reset();
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        //设置响应文件名
        String browserFileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + FilenameUtils.getExtension(originFilePath);
        response.setHeader("Content-Disposition", "attachment;filename=" + browserFileName);
        //文件下载
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            byte[] bytes = null;
            if (originFilePath == "") {
                bytes = FileUtil.readBytes(resource.getFile());
            } else {
                bytes = FileUtil.readBytes(originFilePath);
            }
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
            response.setStatus(200);
            //这里setStatus之后的内容都是不必要的
            //但是可以成功实现在下载文件时返回参数
            HashMap<String, Object> map = new HashMap<>();
            map.put("code",200);
            map.put("msg","文件下载成功捏");
            response.setHeader("X-Result-Data", JSONUtil.toJsonStr(map));
        } catch (IOException e) {
            e.printStackTrace();
            HashMap<String, Object> map = new HashMap<>();
            response.setStatus(500);
            map.put("code",500);
            map.put("msg","网络繁忙,请稍后再试XD");
            response.setHeader("X-Result-Data", JSONUtil.toJsonStr(map));
        }

    }
}
