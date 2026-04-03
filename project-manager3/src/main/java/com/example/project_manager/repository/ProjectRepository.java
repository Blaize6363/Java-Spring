package com.example.project_manager.repository;

import com.example.project_manager.entity.Project;
import com.example.project_manager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> { }
