package com.win.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public void handleSystemException(final Exception pException, final HttpServletResponse pResponse)
            throws IOException {
        pResponse.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Some Error Occurred!!!");
    }
}