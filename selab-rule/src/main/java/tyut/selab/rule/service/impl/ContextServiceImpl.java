package tyut.selab.rule.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.R;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import tyut.selab.rule.service.ContextService;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class ContextServiceImpl implements ContextService {

    @Override
    public void showfiles(String filename, HttpServletResponse res) throws IOException {
        String path = "D://md.md";
        res.setCharacterEncoding("UTF-8");
        // attachment是以附件的形式下载，inline是浏览器打开
        res.setHeader("Content-Disposition", "inline;filename="+filename+".txt");
        res.setContentType("text/plain;UTF-8");
        // 把二进制流放入到响应体中
        ServletOutputStream os = res.getOutputStream();
        File file = new File(path);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }

    @Override
    public void editdfile(HttpServletRequest request) throws IOException {
        InputStream input= request.getInputStream();
        String fileName="D://md.md";
        File file=new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        BufferedInputStream in=new BufferedInputStream(input);
        BufferedOutputStream out=new BufferedOutputStream(new FileOutputStream(fileName));
        int len=-1;
        byte[] b=new byte[1024];
        while((len=in.read(b))!=-1){
            out.write(b,0,len);
        }
        in.close();
        out.close();
    }
}
