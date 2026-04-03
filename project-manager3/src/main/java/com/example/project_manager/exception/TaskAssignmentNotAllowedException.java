package com.example.project_manager.exception;

public class TaskAssignmentNotAllowedException extends RuntimeException {
    public TaskAssignmentNotAllowedException(String message) {
        super(message);
    }
}
