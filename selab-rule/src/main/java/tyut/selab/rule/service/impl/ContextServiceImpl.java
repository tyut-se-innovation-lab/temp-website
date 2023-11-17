package tyut.selab.rule.service.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tyut.selab.rule.service.ContextService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
//
//  readPath: D://md.md
//          uploadPath: D://12354.md
@Service
public class ContextServiceImpl implements ContextService {
    @Value("${rule.readPath}")
    private String pathName;
    @Value("${rule.uploadPath}")
    private String uploadName;
    @Override
    public void showfiles( HttpServletResponse res) throws IOException {
        String fileName =pathName;
        res.setCharacterEncoding("UTF-8");
        // attachment是以附件的形式下载，inline是浏览器打开
//        res.setHeader("Content-Disposition", "inline;filename="+filename+".txt");
        res.setContentType("text/plain;UTF-8");
        // 把二进制流放入到响应体中
        ServletOutputStream os = res.getOutputStream();
        File file = new File(fileName);
        byte[] bytes = FileUtils.readFileToByteArray(file);
        os.write(bytes);
        os.flush();
        os.close();
    }

    @Override
    public void editdfile(HttpServletRequest request) throws IOException {
        InputStream input= request.getInputStream();
        String fileName=pathName;
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
