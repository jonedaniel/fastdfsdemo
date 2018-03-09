package com.huamu668.fastdfsdemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class FileUpload extends BaseDomain {
    private Long id;
    private Long userId;
    private String userType;
    private String fileName;
    private String fileType;
    private String filePath;
    private Date uploadDate;
    private Long code;
}