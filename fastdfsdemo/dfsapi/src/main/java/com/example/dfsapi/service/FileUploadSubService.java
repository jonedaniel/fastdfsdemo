package com.example.dfsapi.service;


import com.example.dfsapi.model.FileUploadSub;

import java.util.List;

public interface FileUploadSubService {

    void save(FileUploadSub fileUploadSub);

    int update(FileUploadSub fileUploadSub);

    FileUploadSub get(Long id);

    List<FileUploadSub> getAll();

    void delete(Long id);
}
