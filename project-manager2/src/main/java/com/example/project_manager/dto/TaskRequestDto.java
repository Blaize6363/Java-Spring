package com.example.project_manager.dto;

import com.example.project_manager.enums.TaskPriority;
import com.example.project_manager.enums.TaskStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@NoArgsConstructor
public class TaskRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 120)
    private String title;
    @Size(max = 500)
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;
    @NotNull
    @NotEmpty
    @Pattern(regexp = "^(TODAY|FUTURE)?")
    private String dueDate;
    private Long projectId;

    public TaskRequestDto(String title, String description, TaskStatus status, TaskPriority priority, String dueDate, Long projectId){
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.projectId = projectId;
    }
}
