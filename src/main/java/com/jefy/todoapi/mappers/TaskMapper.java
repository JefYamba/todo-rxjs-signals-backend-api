package com.jefy.todoapi.mappers;

import com.jefy.todoapi.dtos.TaskResponseDTO;
import com.jefy.todoapi.entities.Task;
import com.jefy.todoapi.entities.TaskDuration;
import com.jefy.todoapi.enums.Status;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 13/06/2024
 */
@Component
public class TaskMapper {
    public TaskResponseDTO fromEntity(Task task) {
        return TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .content(task.getContent())
                .status(task.getStatus())
                .createdAt(task.getCreatedAt())
                .duration(task.getStatus() == Status.COMPLETED ? getDuration(task.getStartedAt(), task.getCompletedAt()) : null)
                .build();
    }

    private TaskDuration getDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        Duration duration = Duration.between(startDateTime, endDateTime);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;
        return TaskDuration.builder()
                .days(days)
                .hours(hours)
                .minutes(minutes)
                .seconds(seconds)
                .build();
    }
}
