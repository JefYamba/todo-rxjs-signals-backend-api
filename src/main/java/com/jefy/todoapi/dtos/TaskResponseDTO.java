package com.jefy.todoapi.dtos;

import com.jefy.todoapi.entities.TaskDuration;
import com.jefy.todoapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 13/06/2024
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String content;
    private Status status;
    private LocalDateTime createdAt;
    private TaskDuration duration;
}
