package com.msbahrddin.upscaletest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException {


    private String resourceName;
    private String fieldValue;

    public EmailAlreadyExistException(String resourceName, String fieldValue) {
        super(String.format("%s with email : '%s' already exist!", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
