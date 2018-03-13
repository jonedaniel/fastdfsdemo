package com.example.dfsserver.mapper;

import com.example.dfsapi.model.FileUpload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface FileUploadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(FileUpload record);

    FileUpload selectByPrimaryKey(Long id);

    List<FileUpload> selectAll();

    int updateByPrimaryKey(FileUpload record);

    FileUpload selectCodeByUserIdFileName(@Param("id") Long id, @Param("fileName") String fileName);

}