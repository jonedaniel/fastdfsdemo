package com.huamu668.fastdfsdemo.service.impl;

import com.huamu668.fastdfsdemo.service.LocalUploadService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LocalUploadServiceImpl implements LocalUploadService {


    @Override
    public void upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String category = originalFilename.substring(originalFilename.lastIndexOf(".")+1);
        String fileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));
        String filePath = "";
    }
}
