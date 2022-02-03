package com.cmundo.printingservicesapi.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
@Setter
public class Error implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String message;
}
