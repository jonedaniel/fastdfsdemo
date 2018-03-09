package com.huamu668.fastdfsdemo.mapper;

import com.huamu668.fastdfsdemo.domain.FileUploadSub;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileUploadSubMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileUploadSub record);

    FileUploadSub selectByPrimaryKey(Long id);

    List<FileUploadSub> selectAll();

    int updateByPrimaryKey(FileUploadSub record);
}