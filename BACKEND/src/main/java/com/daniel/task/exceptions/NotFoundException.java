package com.daniel.task.exceptions;

import com.daniel.task.dtos.ErrorDto;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

public class NotFoundException extends TaskException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, String code) {
        super(message, HttpStatus.NOT_FOUND.value(), code);
    }

    public NotFoundException(String message, String code, ErrorDto data) {
        super(message, HttpStatus.NOT_FOUND.value(), code, Arrays.asList(data));
    }
}
