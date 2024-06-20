package com.jefy.todoapi.services;

import com.jefy.todoapi.dtos.TaskRequestDTO;
import com.jefy.todoapi.dtos.TaskResponseDTO;
import org.springframework.data.domain.Page;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 13/06/2024
 */
public interface TaskService {
    Page<TaskResponseDTO> getAll(String title, int page, int size);
    TaskResponseDTO get(Long id);
    TaskResponseDTO startTask(Long id);
    TaskResponseDTO completeTask(Long id);
    TaskResponseDTO create(TaskRequestDTO dto);
    TaskResponseDTO update(TaskRequestDTO dto);
    boolean delete(Long id);
}
