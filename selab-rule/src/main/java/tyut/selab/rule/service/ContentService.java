package tyut.selab.rule.service;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ContentService {

    AjaxResult uploadMarkdown(HttpServletRequest request, MultipartFile file);


    void downloadMarkdown(HttpServletResponse response);
}
