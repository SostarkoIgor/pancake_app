package com.igor.pancake.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PancakeEditException extends RuntimeException{
    public PancakeEditException(String message) {
        super(message);
    }
}