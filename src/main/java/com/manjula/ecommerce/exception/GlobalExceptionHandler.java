package com.manjula.ecommerce.exception;

import com.manjula.ecommerce.dto.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        ApiResponse<Map<String, String>> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage("Validation failed");
        response.setData(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle resource not found
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleResourceNotFound(
            ResourceNotFoundException ex, WebRequest request) {

        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // Handle constraint violations
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleConstraintViolation(
            ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation ->
                errors.put(violation.getPropertyPath().toString(), violation.getMessage()));

        ApiResponse<Map<String, String>> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage("Constraint violation");
        response.setData(errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<String>> handleGlobalException(
            Exception ex, WebRequest request) {

        ApiResponse<String> response = new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage("An error occurred: " + ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}