package com.demo15.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ErrorDetailsDto {
    private Date date;
    private String msg;
    private String request;
}
