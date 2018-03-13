package com.example.dfsserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dfsapi.model.FileUploadSub;
import com.example.dfsapi.service.FileUploadSubService;
import com.example.dfsserver.mapper.FileUploadSubMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class FileUploadSubServiceImpl implements FileUploadSubService {
    @Autowired
    private FileUploadSubMapper fileUploadSubMapper;

    @Override
    public void save(FileUploadSub fileUploadSub) {
        fileUploadSubMapper.insert(fileUploadSub);
    }

    @Override
    public int update(FileUploadSub fileUploadSub) {
        return fileUploadSubMapper.updateByPrimaryKey(fileUploadSub);
    }

    @Override
    public FileUploadSub get(Long id) {
        return fileUploadSubMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<FileUploadSub> getAll() {
        return fileUploadSubMapper.selectAll();
    }

    @Override
    public void delete(Long id) {
        fileUploadSubMapper.deleteByPrimaryKey(id);
    }
}
