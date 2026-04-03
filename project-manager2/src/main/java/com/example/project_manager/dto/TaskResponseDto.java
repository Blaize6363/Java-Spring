package com.example.project_manager.dto;

import com.example.project_manager.enums.TaskPriority;
import com.example.project_manager.enums.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private String dueDate;
    private LocalDateTime createdAt;
    private Long projectId;

    public TaskResponseDto(Long id, String title, String description, TaskStatus status, TaskPriority priority, String dueDate, LocalDateTime createdAt, Long projectId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.projectId= projectId;
    }
}
