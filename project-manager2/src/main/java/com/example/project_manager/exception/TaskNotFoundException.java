package com.example.project_manager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId) {
        super("Task with id: " + taskId + " not found");
    }
}
