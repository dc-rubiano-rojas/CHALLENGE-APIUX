package com.daniel.task.exceptions;

import com.daniel.task.dtos.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class InternalServerErrorException extends TaskException {
    private static final long serialVersionUID = 1L;

    public InternalServerErrorException(String message, String code) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR.value(), code);
    }

    public InternalServerErrorException(String message, String code, ErrorDto data) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR.value(), code, Arrays.asList(data));
    }
}