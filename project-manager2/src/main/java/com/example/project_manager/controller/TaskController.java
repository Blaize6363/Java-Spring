package com.example.project_manager.controller;

import com.example.project_manager.dto.TaskRequestDto;
import com.example.project_manager.dto.TaskResponseDto;
import com.example.project_manager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@Valid
public class TaskController {

    private final TaskService tasks;

    @Autowired
    public TaskController(TaskService tasks) {
        this.tasks = tasks;
    }

    @PostMapping("/{projectId}/tasks")
    public ResponseEntity<TaskResponseDto> addTask(@PathVariable Long projectId, @Valid @RequestBody TaskRequestDto task) {
        return new ResponseEntity(tasks.createTask(projectId, task), HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}/tasks")
    public ResponseEntity<List<TaskResponseDto>> getTasks(@PathVariable Long projectId) {
        return ResponseEntity.ok(tasks.getTasksByProject(projectId));
    }

    @GetMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskResponseDto> getTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        return ResponseEntity.ok(tasks.getTaskById(projectId, taskId));
    }

    @PutMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long projectId, @PathVariable Long taskId, @Valid @RequestBody TaskRequestDto task) {
        return ResponseEntity.ok(tasks.updateTask(projectId, taskId, task));
    }

    @DeleteMapping("/{projectId}/tasks/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        tasks.deleteTask(projectId, taskId);
        return ResponseEntity.noContent().build();
    }
}
