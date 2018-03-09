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

import java.util.HashMap;
import java.util.Map;

@Controller
public class UploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${server.context-path}")
    private String ctx;

    @Autowired
    private FileUploadService fileUploadService;

    @ModelAttribute
    public void modelInit(Model model) {
        model.addAttribute("ctx", ctx);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public String uploadList(Model model) {
        return "auth_material_upload";
    }

    @RequestMapping(method = RequestMethod.POST, value = "singleUpload")
    @ResponseBody
    public Object singleUpload(MultipartFile file) {
        logger.info(">>>上传文件:{}", file.getOriginalFilename());
        Map<Object, Object> map = new HashMap<>();
        try {
            Long fileId = fileUploadService.save(file.getBytes(), file.getOriginalFilename());

            map.put("success", true);
            map.put("fileId",fileId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

}
