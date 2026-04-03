package com.example.project_manager.service;

import com.example.project_manager.dto.TaskRequestDto;
import com.example.project_manager.dto.TaskResponseDto;
import com.example.project_manager.entity.Project;
import com.example.project_manager.entity.Task;
import com.example.project_manager.enums.TaskStatus;
import com.example.project_manager.exception.*;
import com.example.project_manager.repository.ProjectRepository;
import com.example.project_manager.repository.TaskRepository;
import com.example.project_manager.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.project_manager.entity.Users;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private final UserRepository users;
    private final ProjectRepository projects;
    private final TaskRepository tasks;

    public TaskServiceImpl(ProjectRepository projects, TaskRepository tasks, UserRepository users) {
        this.projects = projects;
        this.tasks = tasks;
        this.users = users;
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
        if(tasks.findAllByProjectId(projectId).stream().map(this::toResponseDto).filter(p -> p.getId()
                .equals(taskId)).toList().isEmpty()) throw new TaskProjectMismatchException(taskId, projectId);
        return tasks.findAllByProjectId(projectId).stream().map(this::toResponseDto).filter(p -> p.getId().equals(taskId)).findAny().orElseThrow(ProjectNotFoundException::new);
    }

    @Override
    public TaskResponseDto updateTask(Long projectId, Long taskId, TaskRequestDto task) {
        task.setProjectId(projectId);
        if (!tasks.existsById(taskId)) throw new TaskNotFoundException(taskId);
        if (!tasks.existsByProjectId(projectId)) throw new ProjectNotFoundException();
        if(tasks.findAllByProjectId(projectId).stream().map(this::toResponseDto).filter(p -> p.getId()
                .equals(taskId)).toList().isEmpty()) throw new TaskProjectMismatchException(taskId, projectId);
        Task update = toEntity(task);
        update.setId(taskId);
        Project project = projects.findById(projectId).orElseThrow();
        update.setProject(project);
        if (update.getStatus().equals(TaskStatus.DONE) && toResponseDto(tasks.findById(taskId).orElseThrow()).getAssignedUserId() == null) throw new TaskAssignmentNotAllowedException("User must be assigned to a task for the status to be change to DONE");
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

    @Override
    public TaskResponseDto assignUser(Long projectId, Long taskId, Long userId) {
        Task taskTest = tasks.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (!projects.existsById(projectId)) throw new ProjectNotFoundException();
        if (!users.existsById(userId)) throw new UserNotFoundException("User with id " + userId + " could not be found");
        if (!tasks.findAllByProjectId(projectId).contains(taskTest)) throw new TaskProjectMismatchException(taskId, projectId);
        Task task = tasks.findAllByProjectId(projectId).stream().filter(t -> t.getProject().getId().equals(projectId)).findAny().orElseThrow();
        if (task.getStatus().equals(TaskStatus.DONE)) throw new TaskAssignmentNotAllowedException("This task is already complete. Completed tasks cannot be assigned to other users");
        task.setUser(users.getReferenceById(userId));
        tasks.save(task);
        return toResponseDto(task);
    }

    @Override
    public void removeUser(Long projectId, Long taskId) {
        Task task = tasks.findAllByProjectId(projectId).stream().filter(t -> t.getId().equals(taskId)).findAny().orElseThrow();
        task.setUser(null);
        tasks.save(task);
    }

    public TaskResponseDto toResponseDto(Task task) {
        return new TaskResponseDto(task.getId(), task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(),
                task.getDueDate(), task.getCreatedAt(), task.getProject().getId(), task.getUser().getId(), task.getUser().getUsername());
    }

    public Task toEntity(TaskRequestDto task) {
        return new Task(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getDueDate());
    }
}
