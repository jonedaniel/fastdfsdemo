package com.example.dfsapi.service;

import com.example.dfsapi.model.FileUpload;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface FileUploadService{
    List<FileUpload> getList();

    FileUpload getFile(Long id);

    void save(FileUpload fileUpload);

    void update(FileUpload fileUpload);

    List<FileUpload> getAll();

    void singleUpload(byte[] file1, String originalFilename) throws Exception;

    void multiUpload(Map<byte[], String> fileMap)throws Exception;

    void download(Long id, HttpServletResponse response);

    void delete(Long id);
}
