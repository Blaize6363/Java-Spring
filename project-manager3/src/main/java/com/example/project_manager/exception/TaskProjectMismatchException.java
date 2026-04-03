package com.example.project_manager.exception;

public class TaskProjectMismatchException extends RuntimeException {
    public TaskProjectMismatchException(Long taskId, Long projectId) {
        super("No task with id: " + taskId + " in project with id of " + projectId);
    }
}
