package com.igor.pancake.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderPancakeException extends RuntimeException{
    public InvalidOrderPancakeException(String message) {
        super(message);
    }
}
