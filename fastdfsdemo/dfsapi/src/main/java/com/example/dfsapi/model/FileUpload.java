package com.example.dfsapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUpload implements Serializable{
    private Long id;

    private Long userId;

    private String userType;

    private String fileName;

    private String fileType;

    private String filePath;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadDate;

    private Long code;

    private List<FileUploadSub> fileUploadSubs = new ArrayList<>();

    public List<FileUploadSub> getFileUploadSubs() {
        return fileUploadSubs;
    }

    public void setFileUploadSubs(List<FileUploadSub> fileUploadSubs) {
        this.fileUploadSubs = fileUploadSubs;
    }

    public static enum ImgType {
        JPEG("jpeg"),
        JPG("jpg"),
        IMP("imp"),
        PNG("png");

        ImgType(String value) {
            this.value = value;
        }

        private String value;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public static boolean contains(String name) {
            if (name.equals(JPEG.getValue()) || name.equals(JPG.getValue()) || name.equals(IMP.getValue()) || name.equals(PNG.getValue())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "FileUpload{" +
                "id=" + id +
                ", userId=" + userId +
                ", userType='" + userType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", filePath='" + filePath + '\'' +
                ", uploadDate=" + uploadDate +
                ", code=" + code +
                ", fileUploadSubs=" + fileUploadSubs +
                '}';
    }
}