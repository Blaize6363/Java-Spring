package com.example.project_manager.entity;

import com.example.project_manager.enums.TaskPriority;
import com.example.project_manager.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    private String title;
    @Getter
    private String description;
    @Getter
    private TaskStatus status;
    @Getter
    private TaskPriority priority;
    @Getter
    private String dueDate;
    @Getter
    private LocalDateTime createdAt;
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;
    @ManyToOne(optional = true)
    @JoinColumn(name = "assigned_user_id")
    private Users user;

    public Task(String title, String description, TaskStatus status, TaskPriority priority, String dueDate) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        createdAt = LocalDateTime.now();
        user = null;
    }

    public Users getUser() {
        return Objects.requireNonNullElseGet(user, () -> new Users("None", "None"));
    }
}
