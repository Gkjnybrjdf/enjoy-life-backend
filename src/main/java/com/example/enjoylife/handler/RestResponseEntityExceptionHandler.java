package com.example.enjoylife.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<HashMap<String, String>> handleConflict(
            RuntimeException ex,
            WebRequest request) {
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("errorMessage", ex.getMessage());
        return ResponseEntity.internalServerError()
                .body(stringStringHashMap);
    }
}
