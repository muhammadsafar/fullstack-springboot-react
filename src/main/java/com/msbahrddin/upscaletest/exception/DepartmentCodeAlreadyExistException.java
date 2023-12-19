package com.msbahrddin.upscaletest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DepartmentCodeAlreadyExistException extends RuntimeException {


    private String resourceName;
    private String fieldValue;

    public DepartmentCodeAlreadyExistException(String resourceName, String fieldValue) {
        super(String.format("%s with department code : '%s' already exist!", resourceName, fieldValue));
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }
}
