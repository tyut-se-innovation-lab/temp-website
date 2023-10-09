package tyut.selab.rule.controller;
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tyut.selab.rule.service.ContextService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@RestController
@RequestMapping("/file")
public class ContextContorller {
    @Autowired
    ContextService contextService;

    @GetMapping("/show")
    public AjaxResult showfiles(String filename, HttpServletResponse res) throws IOException {
        contextService.showfiles(filename,res);
        return AjaxResult.success();
    }

    @PreAuthorize("@ss.hasAnyRoles('admin')")
    @PostMapping("/edit")
    public AjaxResult editfile(HttpServletRequest req) throws IOException {
        contextService.editdfile(req);
        return AjaxResult.success();
    }

}

