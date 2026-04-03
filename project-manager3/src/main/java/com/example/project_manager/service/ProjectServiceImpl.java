package com.example.project_manager.service;

import com.example.project_manager.dto.ProjectRequestDto;
import com.example.project_manager.dto.ProjectResponseDto;
import com.example.project_manager.entity.Project;
import com.example.project_manager.exception.ProjectNotFoundException;
import com.example.project_manager.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public ProjectResponseDto create(ProjectRequestDto project) {
        Project save = projectRepository.save(toEntity(project));
        return toResponseDto(save);
    }

    @Override
    public List<ProjectResponseDto> getAll() {
        return projectRepository.findAll().stream().map(this::toResponseDto).toList();
    }

    @Override
    public ProjectResponseDto getById(Long id) {
        Project project = projectRepository.findById(id).orElseThrow(ProjectNotFoundException::new);
        return toResponseDto(project);
    }

    @Override
    public ProjectResponseDto update(Long id, ProjectRequestDto project) {
        if (!projectRepository.existsById(id)) throw new ProjectNotFoundException();
        Project save = toEntity(project);
        save.setId(id);
        projectRepository.save(save);
        return toResponseDto(save);
    }

    @Override
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) throw new ProjectNotFoundException();
        projectRepository.deleteById(id);
    }

    public ProjectResponseDto toResponseDto(Project project) {
        return new ProjectResponseDto(project.getId(), project.getName(), project.getDescription(), project.getStatus(), project.getCreatedAt());
    }

    public Project toEntity(ProjectRequestDto projectRequestDto) {
        return new Project(projectRequestDto.getName(), projectRequestDto.getDescription(), projectRequestDto.getStatus());
    }
}
