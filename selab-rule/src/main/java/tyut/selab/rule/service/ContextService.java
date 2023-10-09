package tyut.selab.rule.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface ContextService {
    void showfiles(String filename, HttpServletResponse res) throws IOException;

    void editdfile(HttpServletRequest res) throws IOException;

    void upload(MultipartFile file) throws IOException;
}
