package com.jefy.todoapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.time.Instant;
import java.util.Map;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 13/06/2024
 */
@AllArgsConstructor
@Builder
@Getter
public class ApiResponse {
    private Instant timestamp;
    private int statusCode;
    private HttpStatus status;
    private String message;
    private String error;
    private Map<String, ?> data;
}
