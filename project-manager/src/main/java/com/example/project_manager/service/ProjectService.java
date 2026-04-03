package com.example.project_manager.service;

import com.example.project_manager.dto.ProjectRequestDto;
import com.example.project_manager.dto.ProjectResponseDto;
import com.example.project_manager.entity.Project;

import java.util.List;

public interface ProjectService {
    ProjectResponseDto create(ProjectRequestDto project);
    List<ProjectResponseDto> getAll();
    ProjectResponseDto getById(Long id);
    ProjectResponseDto update(Long id, ProjectRequestDto project);
    void delete(Long id);
}
