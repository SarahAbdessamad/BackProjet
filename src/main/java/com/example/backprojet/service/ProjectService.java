package com.example.backprojet.service;

import com.example.backprojet.service.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ProjectService {

    private  ProjectRepo projectRepo;
    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }
    /*
    public Project addEmployee(Project project) {
        project.setProjectName(UUID.randomUUID().toString());
        return ProjectRepo.save(project);
    }

     */

    public void deleteProject(Long ProjectId){
        projectRepo.deleteById(ProjectId);
    }
}