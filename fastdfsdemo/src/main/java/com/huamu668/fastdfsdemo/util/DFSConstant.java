package com.huamu668.fastdfsdemo.util;

public enum DFSConstant {

    NAME("ZMH"), AGE("25"), SALARY("30000"), TIME("2015"), DARE("big");

    private String val;

    private DFSConstant(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
