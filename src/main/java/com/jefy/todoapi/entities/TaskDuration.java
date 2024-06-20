package com.jefy.todoapi.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 17/06/2024
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TaskDuration {
    private long days;
    private long hours;
    private long minutes;
    private long seconds;
}
