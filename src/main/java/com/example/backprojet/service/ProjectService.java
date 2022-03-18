package com.example.backprojet.service;

import com.example.backprojet.model.Project;
import com.example.backprojet.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class ProjectService {
    private  ProjectRepo projectRepo;
    /*@Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }
    public Project addEmployee(Project project) {
        project.setProjectName(UUID.randomUUID().toString());
        return ProjectRepo.save(project);
    }

     */
}