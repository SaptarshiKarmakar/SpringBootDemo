package com.demo15.exception;

import com.demo15.util.ErrorDetailsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandelException {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorDetailsDto> employeeNotFoundException(GlobalException e, WebRequest request){
        ErrorDetailsDto errorDetailsDto = new ErrorDetailsDto(new Date(), e.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetailsDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
