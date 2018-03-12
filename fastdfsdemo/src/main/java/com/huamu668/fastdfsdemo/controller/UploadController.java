package com.huamu668.fastdfsdemo.controller;

import com.huamu668.fastdfsdemo.service.FileUploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController extends BaseController{

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Value("${downloadUrl}")
    private String downloadUrl;

    @Autowired
    private FileUploadService fileUploadService;

    @ModelAttribute
    public void modelInit(Model model) {
        model.addAttribute("downloadUrl", downloadUrl);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String uploadList(Model model) {
        return "auth_material_upload";
    }

    @RequestMapping(method = RequestMethod.POST, value = "singleUpload")
    @ResponseBody
    public Object singleUpload(MultipartFile file) {
        LOG.info(">>>上传文件:{}", file.getOriginalFilename());
        Map<Object, Object> map = new HashMap<>();
        try {
            Long fileId = fileUploadService.save(file.getBytes(), file.getOriginalFilename());

            map.put("success", true);
            map.put("fileId",fileId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", e.getMessage());
            LOG.warn("上传文件失败");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/multi_upload")
    @ResponseBody
    public Object multiUplaod(MultipartFile[] files) {
        Map<Object, Object> map = new HashMap<>();
        LOG.info("开始多文件上传");
        try {
            for (MultipartFile file : files) {
                fileUploadService.save(file.getBytes(), file.getOriginalFilename());
            }
            map.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            LOG.warn("上传多文件失败");
        }
        return map;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/download")
    @ResponseBody
    public void download(Long id, HttpServletResponse response) {
        LOG.info(">>>下载文件<<<文件id"+id);
        try {
            fileUploadService.download(id, response);
        } catch (RuntimeException e) {
            e.printStackTrace();
            LOG.warn(">>>下载文件失败");
        }
    }


}
