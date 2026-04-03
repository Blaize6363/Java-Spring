package com.example.project_manager.service;

import com.example.project_manager.dto.TaskRequestDto;
import com.example.project_manager.dto.TaskResponseDto;
import com.example.project_manager.entity.Project;
import com.example.project_manager.entity.Task;
import com.example.project_manager.exception.ProjectNotFoundException;
import com.example.project_manager.exception.TaskNotFoundException;
import com.example.project_manager.exception.TaskProjectMismatchException;
import com.example.project_manager.repository.ProjectRepository;
import com.example.project_manager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TaskServiceImpl implements TaskService{

    private final ProjectRepository projects;
    private final TaskRepository tasks;

    public TaskServiceImpl(ProjectRepository projects, TaskRepository tasks) {
        this.projects = projects;
        this.tasks = tasks;
    }


    @Override
    public TaskResponseDto createTask(Long projectId, TaskRequestDto task) {
        Project project = projects.findById(projectId).orElseThrow(ProjectNotFoundException::new);
        Task save = toEntity(task);
        save.setProject(project);
        tasks.save(save);
        return toResponseDto(save);
    }

    @Override
    public List<TaskResponseDto> getTasksByProject(Long projectId) {
        List<TaskResponseDto> result = tasks.findAllByProjectId(projectId).stream().map(this::toResponseDto).toList();
        if (result.isEmpty()) throw new ProjectNotFoundException();
        return result;
    }

    @Override
    public TaskResponseDto getTaskById(Long projectId, Long taskId) {
        if(tasks.findAllByProjectId(projectId).stream().map(this::toResponseDto).filter(p -> p.getId().equals(taskId)).toList().isEmpty()) throw new TaskProjectMismatchException(taskId, projectId);
        return tasks.findAllByProjectId(projectId).stream().map(this::toResponseDto).filter(p -> p.getId().equals(taskId)).findAny().orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public TaskResponseDto updateTask(Long projectId, Long taskId, TaskRequestDto task) {
        task.setProjectId(projectId);
        if(tasks.findAllByProjectId(projectId).stream().map(this::toResponseDto).filter(p -> p.getId().equals(taskId)).toList().isEmpty()) throw new TaskProjectMismatchException(taskId, projectId);
        if (!tasks.existsById(taskId)) throw new TaskNotFoundException(taskId);
        if (!tasks.existsByProjectId(projectId)) throw new ProjectNotFoundException();
        Task update = toEntity(task);
        update.setId(taskId);
        Project project = projects.findById(projectId).orElseThrow();
        update.setProject(project);
        tasks.save(update);
        return toResponseDto(update);
    }

    @Override
    public void deleteTask(Long projectId, Long taskId) {
        Task task = tasks.findById(taskId).orElseThrow();
        if (task.getProject().getId().equals(projectId)) {
            tasks.delete(task);
        } else {
            throw new TaskNotFoundException(taskId);
        }
    }

    public TaskResponseDto toResponseDto(Task task) {
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDueDate(), task.getCreatedAt(), task.getProject().getId());
    }

    public Task toEntity(TaskRequestDto task) {
        return new Task(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDueDate());
    }
}
