package com.jefy.todoapi.services.impl;

import com.jefy.todoapi.dtos.TaskRequestDTO;
import com.jefy.todoapi.dtos.TaskResponseDTO;
import com.jefy.todoapi.entities.Task;
import com.jefy.todoapi.enums.Status;
import com.jefy.todoapi.mappers.TaskMapper;
import com.jefy.todoapi.repositories.TaskRepository;
import com.jefy.todoapi.services.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 13/06/2024
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public Page<TaskResponseDTO> getAll(String title, int page, int size) {
        return taskRepository.findByTitleContaining(title, PageRequest.of(page,size, Sort.by(Sort.Direction.DESC,"createdAt"))).map(taskMapper::fromEntity);
    }

    @Override
    public TaskResponseDTO get(Long id) {
        return taskRepository.findById(id).map(taskMapper::fromEntity).orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
    }

    @Override
    public TaskResponseDTO startTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
        if (task.getStatus() == Status.ON_GOING || task.getStatus() == Status.COMPLETED) { throw new IllegalArgumentException(String.format("Can't start %s task", task.getStatus() == Status.ON_GOING ? "an already started":"a completed"));}

        task.setStatus(Status.ON_GOING);
        task.setStartedAt(LocalDateTime.now());

        return taskMapper.fromEntity(taskRepository.save(task));
    }

    @Override
    public TaskResponseDTO completeTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Task does not exist"));
        if (task.getStatus() == Status.NOT_STARTED || task.getStatus() == Status.COMPLETED) { throw new IllegalArgumentException(String.format("Can't complete %s task", task.getStatus() == Status.NOT_STARTED ? "a non started":"an already completed"));}

        task.setStatus(Status.COMPLETED);
        task.setCompletedAt(LocalDateTime.now());

        return taskMapper.fromEntity(taskRepository.save(task));
    }

    @Override
    public TaskResponseDTO create(TaskRequestDTO dto) {
        Task task = Task.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .status(Status.NOT_STARTED)
                .createdAt(LocalDateTime.now())
                .build();
        return taskMapper.fromEntity(taskRepository.save(task));
    }

    @Override
    public TaskResponseDTO update(TaskRequestDTO dto) {
        if (dto != null && dto.getId() != null) {
            Task task = taskRepository.findById(dto.getId()).orElse(null);
            if (task != null) {
                task.setTitle(dto.getTitle());
                task.setContent(dto.getContent());
                return taskMapper.fromEntity(taskRepository.save(task));
            } else {
                throw new IllegalArgumentException("Task not found");
            }
        } else {
            throw new NullPointerException("Task id is null");
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
