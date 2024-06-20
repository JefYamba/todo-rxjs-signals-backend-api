package com.jefy.todoapi.exceptions;

import com.jefy.todoapi.dtos.ApiResponse;
import lombok.Getter;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.View;

import java.time.Instant;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 10/05/2024
 */
@Getter
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final View error;

    public GlobalExceptionHandler(View error) {
        this.error = error;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse> handleIllegalArgumentException(IllegalArgumentException exception){
        return ResponseEntity.status(BAD_REQUEST).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .error(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleEntityNotValidException(MethodArgumentNotValidException exception){
        String errors = exception.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" and "));
        return ResponseEntity.status(BAD_REQUEST).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .status(BAD_REQUEST)
                        .statusCode(BAD_REQUEST.value())
                        .error("form is not valid - " + errors)
                        .build()
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ApiResponse> handleException(NullPointerException exception){
        return ResponseEntity.status(NOT_FOUND).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .status(NOT_FOUND)
                        .statusCode(NOT_FOUND.value())
                        .error(exception.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception exception){
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .status(INTERNAL_SERVER_ERROR)
                        .statusCode(INTERNAL_SERVER_ERROR.value())
                        .error(exception.getMessage())
                        .build()
        );
    }

}
