package com.example.project_manager.service;

import com.example.project_manager.dto.TaskRequestDto;
import com.example.project_manager.dto.TaskResponseDto;
import com.example.project_manager.entity.Task;

import java.util.List;

public interface TaskService {
    public TaskResponseDto createTask(Long projectId, TaskRequestDto task);
    public TaskResponseDto getTaskById(Long projectId, Long taskId);
    public List<TaskResponseDto> getTasksByProject(Long projectId);
    public TaskResponseDto updateTask(Long projectId, Long taskId, TaskRequestDto task);
    void deleteTask(Long projectId, Long taskId);
}
