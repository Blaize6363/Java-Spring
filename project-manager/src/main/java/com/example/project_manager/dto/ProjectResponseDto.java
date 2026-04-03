package com.example.project_manager.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class ProjectResponseDto {
    private Long id;
    private String name;
    private String description;
    private String status;
    private LocalDateTime createdAt;

    public ProjectResponseDto(Long id, String name, String description, String status, LocalDateTime createdAt) {
            this.id = id;
            this.name = name;
            this.description = description;
            this.status = status;
            this.createdAt = createdAt;
    }
}
