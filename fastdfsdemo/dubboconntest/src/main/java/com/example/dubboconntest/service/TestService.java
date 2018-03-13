package com.example.dubboconntest.service;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class TestService implements ITestService {

    public String hello() {
        return "this is me";
    }
}