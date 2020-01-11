package com.ugurcangursen.UserLoginRegister.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class IMExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IMExceptionHandler.class); // Logging APIs

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<?> handleExceptions (Exception ex, WebRequest request) {
        LOGGER.error("ControllerAdvice -> ExceptionHandler -> ", ex , request);
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),ex.getMessage());
        return new ResponseEntity<> (exceptionResponse, HttpStatus.EXPECTATION_FAILED);
    }

}