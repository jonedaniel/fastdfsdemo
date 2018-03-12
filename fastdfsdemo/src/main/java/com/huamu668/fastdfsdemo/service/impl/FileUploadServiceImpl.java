package com.huamu668.fastdfsdemo.service.impl;

import com.huamu668.fastdfsdemo.domain.FileUpload;
import com.huamu668.fastdfsdemo.domain.FileUploadSub;
import com.huamu668.fastdfsdemo.mapper.FileUploadMapper;
import com.huamu668.fastdfsdemo.mapper.FileUploadSubMapper;
import com.huamu668.fastdfsdemo.service.FileUploadService;
import com.huamu668.fastdfsdemo.util.FastDFSClient;
import com.huamu668.fastdfsdemo.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadMapper    fileUploadMapper;
    @Autowired
    private FileUploadSubMapper subMapper;

    @Autowired
    private FastDFSClient fastDFSClient;

    public Long save(byte[] bytes, String originalName) {
        try {
            String filePath = fastDFSClient.uploadFile(bytes, originalName);
            String fileName = FileUtil.getSimpleName(originalName);

            FileUpload fileUpload = new FileUpload();
            fileUpload.setFileName(fileName);
            fileUpload.setFileType(FileUtil.getExtensionName(originalName));
            fileUpload.setUploadDate(new Date());
            fileUpload.setFilePath(filePath);

            //一个用户，只能有一个同名文件,检查是否已经存在同名文件
            FileUpload oldFileUpload = getByFileName(fileName);
            if (oldFileUpload != null) {
                FileUploadSub subFile = new FileUploadSub();
                subFile.setFilePath(oldFileUpload.getFilePath());
                subFile.setSuperId(oldFileUpload.getId());
                subFile.setUploadDate(oldFileUpload.getUploadDate());
                subMapper.insert(subFile);

                fileUpload.setId(oldFileUpload.getId());
                fileUploadMapper.updateByPrimaryKey(fileUpload);
            } else {
                fileUploadMapper.insert(fileUpload);
            }

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

    @Override
    public void download(Long id, HttpServletResponse res) {
        FileUpload fileUpload = fileUploadMapper.selectByPrimaryKey(id);
        String     filePath   = fileUpload.getFilePath();

        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileUpload.getFileName() + "." + fileUpload.getFileType());

        byte[]              buff = new byte[1024];
        BufferedInputStream bis  = null;
        OutputStream        os   = null;
        try {
            byte[] bytes = fastDFSClient.downloadFile(filePath);
            os = res.getOutputStream();
            bis = new BufferedInputStream(new ByteArrayInputStream(bytes));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("上传文件失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
