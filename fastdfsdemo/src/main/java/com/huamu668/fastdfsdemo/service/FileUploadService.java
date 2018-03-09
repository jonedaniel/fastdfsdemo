package com.huamu668.fastdfsdemo.service;

import com.huamu668.fastdfsdemo.domain.FileUpload;

import java.util.List;

public interface FileUploadService {

    Long save(byte[] bytes,String originalName);

    byte[] download(String filePath);

    FileUpload get(Long id);

    void delete(Long id);

    List<FileUpload> listAll();

}
