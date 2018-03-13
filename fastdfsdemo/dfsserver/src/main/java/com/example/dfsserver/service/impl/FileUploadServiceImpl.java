package com.example.dfsserver.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.dfsapi.model.FileUpload;
import com.example.dfsapi.model.FileUploadSub;
import com.example.dfsapi.service.FileUploadService;
import com.example.dfsapi.service.FileUploadSubService;
import com.example.dfsserver.config.FastDFSClient;
import com.example.dfsserver.config.FileUtil;
import com.example.dfsserver.mapper.FileUploadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private FileUploadMapper     fileUploadMapper;
    @Autowired
    private FastDFSClient        fastDFSClient;
    @Autowired
    private FileUploadSubService fileUploadSubService;


    @Override
    public List<FileUpload> getList() {
        return fileUploadMapper.selectAll();
    }

    @Override
    public FileUpload getFile(Long id) {
        return fileUploadMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(FileUpload fileUpload) {
        fileUploadMapper.insert(fileUpload);
    }

    @Override
    public void update(FileUpload fileUpload) {
        fileUploadMapper.updateByPrimaryKey(fileUpload);
    }

    @Override
    public List<FileUpload> getAll() {
        return fileUploadMapper.selectAll();
    }

    @Override
    public void singleUpload(byte[] bytes, String originalName) throws Exception {

        String fileName      = FileUtil.getSimpleName(originalName);
        String filePath      = fastDFSClient.uploadFile(bytes, fileName);
        String extensionName = FileUtil.getExtensionName(originalName);

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileType(extensionName);
        fileUpload.setFilePath(filePath);
        fileUpload.setUploadDate(new Date());
        //对于重复文件,code累加,更新path到最后上传文件 todo
        FileUpload oldFileUpload = getCodeByUserIdFileName(0L, fileName);
        if (oldFileUpload == null) {
            fileUpload.setCode(0L);
            this.save(fileUpload);
        } else {
            fileUpload.setId(oldFileUpload.getId());
            fileUpload.setCode(oldFileUpload.getCode() + 1L);
            this.update(fileUpload);
            //将oldFile存入sub表
            FileUploadSub sub = new FileUploadSub();
            sub.setSuperId(oldFileUpload.getId());
            sub.setFilePath(oldFileUpload.getFilePath());
            sub.setUploadDate(new Date());
            fileUploadSubService.save(sub);
        }

        //切图存储 写在表现层 todo
/*        if (FileUpload.ImgType.contains(extension)) {
            MultipartFile[] chopImgs = getChopImg(file);
            FileUploadSub fileUploadSub;
            for (MultipartFile chopImg : chopImgs) {
                //根据图片名称 给图片一个格式标记
                String filename = chopImg.getOriginalFilename();
                String code        = filename.replace(".", " ").split(" ")[1].split("-")[1];
                fileUploadSub = new FileUploadSub();
                fileUploadSub.setCode(code);
                fileUploadSub.setSuperId(fileUpload.getId());
                fileUploadSub.setUploadDate(new Date());
                String imgPath = fastDFSStorageService.uploadResource(chopImg.getOriginalFilename(), chopImg.getBytes(), new HashMap<>());
                fileUploadSub.setFilePath(imgPath);
                fileUploadSubService.save(fileUploadSub);
            }
        }*/
    }

    private FileUpload getCodeByUserIdFileName(Long id, String fileName) {
        return fileUploadMapper.selectCodeByUserIdFileName(id, fileName);
    }

    @Override
    public void multiUpload(Map<byte[], String> fileMap) throws Exception {
        for (Map.Entry<byte[], String> singleEntry : fileMap.entrySet()) {
            this.singleUpload(singleEntry.getKey(), singleEntry.getValue());
        }
    }

    @Override
    public void download(Long id, HttpServletResponse res) {
        FileUpload fileUpload = this.getFile(id);
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
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

    @Override
    public void delete(Long id) {
        FileUpload fileUpload = this.getFile(id);
        try {
            fastDFSClient.deleteFile(fileUpload.getFilePath());
            List<FileUploadSub> subs = fileUpload.getFileUploadSubs();
            for (FileUploadSub sub : subs) {
                fastDFSClient.deleteFile(sub.getFilePath());
                fileUploadSubService.delete(sub.getId());
            }
            fileUploadMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("删除文件异常");
        }
    }

    /**
     * todo
     *
     * @param file
     * @return
     */
    private MultipartFile[] getChopImg(MultipartFile file) {
        //设置originalName:图片名-大小.jpg
        return new MultipartFile[0];
    }
}
