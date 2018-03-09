package com.huamu668.fastdfsdemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class BaseDomain implements Serializable {
    private Long id;
}
