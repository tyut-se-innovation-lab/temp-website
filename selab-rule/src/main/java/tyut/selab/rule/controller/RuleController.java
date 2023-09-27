package tyut.selab.rule.controller;

import com.ruoyi.common.core.domain.R;
import gui.ava.html.image.generator.HtmlImageGenerator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tyut.selab.rule.service.RuleService;
import tyut.selab.rule.utils.HtmlUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 奖惩制度管理
 */
@Api("奖惩制度管理")
@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    private RuleService ruleService;

    /**
     * 根据user_id查询sys_user表中的is_pop字段,如果为1则弹出奖惩制度窗口，为0则不弹出
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据user_id查询sys_user表中的is_pop字段,如果为1则弹出奖惩制度窗口，为0则不弹出")
    @GetMapping("/getStatus/{user_id}")
    public R getStatus(@PathVariable("user_id") Long userId) {
        return R.ok(ruleService.getStatus(userId));
    }

    /**
     * 当用户点击不再提示奖惩制度窗口后，发送请求修改sys_user表中的is_pop字段
     *
     * @param userId
     * @param ruleStatus
     */
    @ApiOperation("当用户点击不再提示奖惩制度窗口后，发送请求修改sys_user表中的is_pop字段")
    @PutMapping("setStatus")
    public R setStatus(Long userId, int ruleStatus) {
        ruleService.setStatus(userId, ruleStatus);
        return R.ok();
    }

    /**
     * 用户点击修改文本，点击保存后发送请求修改sys_text表中的content字段
     *
     * @param text
     */
    @ApiOperation("用户点击修改文本，点击保存后发送请求修改sys_text表中的content字段")
    @PutMapping("edit")
    public R edit(String text) {
        ruleService.edit(text);
        return R.ok();
    }

    @ApiOperation("下载文件，将text转换为word,markdown等类型")
    @PostMapping("/download")
    public void download(HttpServletResponse response) {//传入下载文件的类型
        String text = ruleService.getText();
        System.out.println(HtmlUtils.markdownToHtmlExtensions(text));
        String s = HtmlUtils.markdownToHtmlExtensions(text);
        HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
        //加载html模版
        imageGenerator.loadHtml(s);
        //把html写入到图片
        OutputStream outputStream = null;
        BufferedImage image = imageGenerator.getBufferedImage();
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();
            outputStream = response.getOutputStream();
            outputStream.write(imageBytes);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户点击播放按钮，发送请求进行播报
     */
    @ApiOperation("用户点击播放按钮，发送请求返回奖惩制度文本内容到前端")
    @GetMapping("/getArticle")
    public R<String> article() {
        return R.ok(ruleService.getText());
    }
}
