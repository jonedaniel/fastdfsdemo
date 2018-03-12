package com.huamu668.fastdfsdemo.controller;

import com.huamu668.fastdfsdemo.service.LocalUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/local")
public class UploadDirectiveController extends BaseController{

    @Autowired
    private LocalUploadService localUploadService;

    @RequestMapping(method = RequestMethod.GET,value = "/list")
    public Object index(Model model) {
        return "direct";
    }

    @RequestMapping(method = RequestMethod.POST,value = "/upload")
    @ResponseBody
    public Object upload(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        localUploadService.upload(file);
        return "success";
    }


}
