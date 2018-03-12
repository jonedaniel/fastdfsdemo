package com.huamu668.fastdfsdemo.service;

import com.huamu668.fastdfsdemo.domain.FileUpload;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileUploadService {

    Long save(byte[] bytes,String originalName);

    byte[] download(String filePath);

    FileUpload get(Long id);

    void delete(Long id);

    List<FileUpload> listAll();

    void download(Long id, HttpServletResponse response);
}
