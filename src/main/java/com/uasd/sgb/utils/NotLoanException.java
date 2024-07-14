package com.uasd.sgb.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotLoanException extends RuntimeException {
    public NotLoanException(String message) {
        super(message);
    }
}
