package com.thoughtworks.springbootemployee.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import com.thoughtworks.springbootemployee.exception.EmployeeNotFound;

@RestControllerAdvice

public class GlobalControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse employeeNotFoundExceptionHandling(EmployeeNotFound employeeNotFound){
        return new ErrorResponse(employeeNotFound.getMessage(),HttpStatus.NOT_FOUND.name());
    }
}


