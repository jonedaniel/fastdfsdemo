package com.huamu668.fastdfsdemo.service;

import org.springframework.web.multipart.MultipartFile;

public interface LocalUploadService {
    void upload(MultipartFile file);
}
