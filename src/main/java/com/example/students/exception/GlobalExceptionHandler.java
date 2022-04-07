package com.example.students.exception;

import com.example.students.constant.ApplicationConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorDetails> studentNotFoundException(final StudentNotFoundException e){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Resource not found",
                e.getMessage(), ApplicationConstants.NO_STUDENT_FOUND_ERROR_CODE);
        return ResponseEntity.status(404).body(errorDetails);
    }
}
