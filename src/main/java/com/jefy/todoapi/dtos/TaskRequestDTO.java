package com.jefy.todoapi.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class TaskRequestDTO {
    private Long id;
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Content cannot be empty")
    private String content;
}
