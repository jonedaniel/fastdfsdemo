package com.example.dfsserver.mapper;

import com.example.dfsapi.model.FileUploadSub;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileUploadSubMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileUploadSub record);

    FileUploadSub selectByPrimaryKey(Long id);

    List<FileUploadSub> selectAll();

    int updateByPrimaryKey(FileUploadSub record);
}