package com.jefy.todoapi.repositories;

import com.jefy.todoapi.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author JefYamba
 * @Email joph.e.f.yamba@gmail.com
 * @Since 13/06/2024
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findByTitleContaining(String title, PageRequest pageRequest);

}
