package com.huamu668.fastdfsdemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Setter@Getter@ToString
public class FileUploadSub extends BaseDomain{
    private Long id;
    private Long superId;
    private String filePath;
    private Date uploadDate;
    private String code;
}