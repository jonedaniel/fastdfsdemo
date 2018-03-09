package com.huamu668.fastdfsdemo.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RespondResult {
    private String code;
    private String msg;


    public RespondResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
