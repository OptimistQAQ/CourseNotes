package controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import pojo.MultiFileDomain;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
public class MultiFilesController {

    //得到一个用来记录日志的对象，
    private static final Log logger = LogFactory.getLog(MultiFilesController.class);

    /*
    * 多文件上传
     */
    @RequestMapping("/multifile")
    public String multiFileUpload(@ModelAttribute MultiFileDomain multiFileDomain,
                                  HttpServletRequest request) {
        String realpath = request.getServletContext().getRealPath("uploadfiles");
        File targetDir = new File(realpath);
        if(!targetDir.exists()) {
            targetDir.mkdirs();
        }
        List<MultipartFile> files = multiFileDomain.getMyfile();
        for (int i=0; i<files.size(); i++) {
            MultipartFile file = files.get(i);
            String filename = file.getOriginalFilename();
            File targetFile = new File(realpath, filename);

            //上传
            try {
                file.transferTo(targetFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        logger.info("成功");
        return "showMulti";
    }

}
