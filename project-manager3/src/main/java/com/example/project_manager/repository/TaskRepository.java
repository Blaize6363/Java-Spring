package com.example.project_manager.repository;

import com.example.project_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByProjectId(Long projectId);
    public boolean existsByProjectId(Long projectId);
}
