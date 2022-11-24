package com.undefined.codeblooded.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = InvalidAccessTokenException.class)
    public ResponseEntity<Map<String, Object>> invalidAccessToken(InvalidAccessTokenException e){
        Map<String, Object> res = new HashMap<>();
        res.put("status", 400);
        res.put("msg", "Invalid Access Token");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> invalidArgument(IllegalArgumentException e){
        Map<String, Object> res = new HashMap<>();
        res.put("status", 400);
        res.put("msg", "Bad Request Structure");
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
    }

}
