package com.example.backprojet.service;

import com.example.backprojet.model.Project;
import com.example.backprojet.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProjectService {

    private  ProjectRepo projectRepo;
    @Autowired
    public ProjectService(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }
    @Autowired
    private JdbcTemplate jdbcTemplate;
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