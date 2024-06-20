package com.jefy.todoapi.controllers;

import com.jefy.todoapi.dtos.ApiResponse;
import com.jefy.todoapi.dtos.TaskRequestDTO;
import com.jefy.todoapi.services.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 13/06/2024
 */
@RestController
@RequestMapping("api/todos")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<ApiResponse> getTasks(
            @RequestParam(value = "q", defaultValue = "") String title,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.status(OK).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .message("Tasks retrieved successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(Map.of("todos", taskService.getAll(title, page, size)))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTask(@PathVariable long id) {
        return ResponseEntity.status(OK).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .message("Task retrieved successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(Map.of("todo", taskService.get(id)))
                        .build()
        );
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(CREATED).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .message("Task added successfully")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .data(Map.of("todo", taskService.create(taskRequestDTO)))
                        .build()
        );
    }

    @GetMapping("/start/{id}")
    public ResponseEntity<ApiResponse> startTask(@PathVariable long id) {
        return ResponseEntity.status(OK).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .message("Task started successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(Map.of("todo", taskService.startTask(id)))
                        .build()
        );
    }

    @GetMapping("/complete/{id}")
    public ResponseEntity<ApiResponse> completTask(@PathVariable long id) {
        return ResponseEntity.status(OK).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .message("Task completed successfully")
                        .status(OK)
                        .statusCode(OK.value())
                        .data(Map.of("todo", taskService.completeTask(id)))
                        .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateTask(@RequestBody @Valid TaskRequestDTO taskRequestDTO) {
        return ResponseEntity.status(ACCEPTED).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .message("Task updated successfully")
                        .status(ACCEPTED)
                        .statusCode(ACCEPTED.value())
                        .data(Map.of("todo", taskService.update(taskRequestDTO)))
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable long id) {
        boolean delete = taskService.delete(id);
        return ResponseEntity.status(ACCEPTED).body(
                ApiResponse.builder()
                        .timestamp(Instant.now())
                        .message(delete ? "Task deleted successfully": "Something went wrong")
                        .error( !delete ? "Something went wrong" : "")
                        .status(ACCEPTED)
                        .statusCode(ACCEPTED.value())
                        .build()
        );

    }
}
