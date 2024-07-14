package com.uasd.sgb.config;


import com.uasd.sgb.dto.response.ExceptionResponse;
import com.uasd.sgb.utils.NotFoundException;
import com.uasd.sgb.utils.NotLoanException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.Objects;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
@ControllerAdvice(annotations = RestController.class)
public class ErrorHandler {


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> exception(IllegalArgumentException ex){
        ExceptionResponse response = ExceptionResponse.builder().
                message(ex.getMessage()).
                code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ExceptionResponse> exception(MissingServletRequestParameterException ex){
        ExceptionResponse response = ExceptionResponse.builder().
                message(ex.getParameterName()+" "+ex.getParameterType()).
                code(BAD_REQUEST.toString())
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionResponse> exception(NoSuchElementException ex){
        ExceptionResponse response = ExceptionResponse.builder().
                message(ex.getMessage()).
                code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> exception(RuntimeException ex){
        ExceptionResponse response = ExceptionResponse.builder().
                message(ex.getMessage()).
                code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> exception(MethodArgumentNotValidException ex){
        ExceptionResponse response = ExceptionResponse.builder().
                message(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage()).
                code(BAD_REQUEST.toString())
                .build();

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> exception(NotFoundException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NotLoanException.class)
    public ResponseEntity<ExceptionResponse> exception(NotLoanException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ExceptionResponse> exception(MissingRequestHeaderException ex){
        ExceptionResponse response = ExceptionResponse.builder()
                .message(ex.getMessage())
                .code(BAD_REQUEST.toString())
                .build();
        return ResponseEntity.status(BAD_REQUEST).body(response);
    }

}