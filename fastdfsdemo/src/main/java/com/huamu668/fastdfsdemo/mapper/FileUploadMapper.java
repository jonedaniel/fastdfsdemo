package com.huamu668.fastdfsdemo.mapper;

import com.huamu668.fastdfsdemo.domain.FileUpload;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUploadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileUpload record);

    FileUpload selectByPrimaryKey(Long id);

    List<FileUpload> selectAll();

    int updateByPrimaryKey(FileUpload record);

    FileUpload selectByFileName(String fileName);
}