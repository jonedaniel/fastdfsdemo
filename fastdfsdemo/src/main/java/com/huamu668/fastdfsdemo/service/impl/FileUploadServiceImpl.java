package com.huamu668.fastdfsdemo.service.impl;

import com.huamu668.fastdfsdemo.domain.FileUpload;
import com.huamu668.fastdfsdemo.mapper.FileUploadMapper;
import com.huamu668.fastdfsdemo.service.FileUploadService;
import com.huamu668.fastdfsdemo.util.FastDFSClient;
import com.huamu668.fastdfsdemo.util.FileUtil;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadMapper fileUploadMapper;

    @Autowired
    private FastDFSClient fastDFSClient;

    public Long save(byte[] bytes, String originalName) {
        try {
            String fileId = fastDFSClient.uploadFile(bytes, originalName);
            String fileName = FileUtil.getSimpleName(originalName);
            /**
             * todo
             * 一个用户，只能有一个同名文件
             * 在这里用文件名搜索数据库，如果有同名文件，证明有新文件要覆盖旧文件，
             * 将旧文件放入从表
             */
            FileUpload oldFileUpload = getByFileName(fileName);

            FileUpload fileUpload = new FileUpload();
            fileUpload.setFilePath(fileId);
            fileUpload.setFileName(fileName);
            fileUpload.setFileType(FileUtil.getExtensionName(originalName));
            fileUpload.setUploadDate(new Date());

            fileUploadMapper.insert(fileUpload);

            return fileUpload.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传文件失败");
        }
    }

    private FileUpload getByFileName(String fileName) {
        return fileUploadMapper.selectByFileName(fileName);
    }

    public byte[] download(String filePath) {
        return fastDFSClient.downloadFile(filePath);
    }

    public FileUpload get(Long id) {
        return fileUploadMapper.selectByPrimaryKey(id);
    }

    public void delete(Long id) {
        try {
            FileUpload fileUpload = fileUploadMapper.selectByPrimaryKey(id);
            fastDFSClient.deleteFile(fileUpload.getFilePath());
            fileUploadMapper.deleteByPrimaryKey(id);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public List<FileUpload> listAll() {
        return fileUploadMapper.selectAll();
    }
}
