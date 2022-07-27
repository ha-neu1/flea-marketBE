package com.fleamarket.demo.Exception.ExceptionHandler;

import com.fleamarket.demo.Exception.MainItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(MainItemException.class)
    protected ResponseEntity<String> handleMainItemException(MainItemException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<String> handleException(NullPointerException e) {
        return new ResponseEntity<>("로그인을 하셨는지 확인해 주세요", HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(NullPointerException.class)
    protected ResponseEntity<String> handleException(NullPointerException e) {
        return new ResponseEntity<>("로그인을 하셨는 지 확인해주세요", HttpStatus.FORBIDDEN);
    }

}
