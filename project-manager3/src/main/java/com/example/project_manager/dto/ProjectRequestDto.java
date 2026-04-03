package com.example.project_manager.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectRequestDto {
    @NotNull
    @NotEmpty
    @Size(min = 3)
    private String name;
    @Size(max = 500)
    private String description;
    @NotNull
    @NotEmpty
    @Pattern(regexp="^(PLANNED|IN_PROGRESS|DONE)?")
    private String status;
}
