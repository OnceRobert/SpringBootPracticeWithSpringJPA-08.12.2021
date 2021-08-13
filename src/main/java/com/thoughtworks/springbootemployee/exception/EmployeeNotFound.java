package com.thoughtworks.springbootemployee.exception;

public class EmployeeNotFound extends RuntimeException{
    String message;

    public EmployeeNotFound(String message) {
        super(message);
    }


}
