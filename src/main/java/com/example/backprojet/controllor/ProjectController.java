package com.example.backprojet.controllor;

import com.example.backprojet.model.Project;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private Project project;
    private  ProjectService projectService;
    @Autowired
    private  ProjectRepo projectRepo;
        public ProjectController(ProjectService projectService){
        this.projectService=projectService;
        }
        /*
    @PostMapping("/addProject")
    public ResponseEntity<Project> addEmployee(@RequestBody Project project) {
      Project newProject = projectRepo.save(project);
    return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }
    */
    @PostMapping("/addproject")
    void addProject (@RequestBody Project project) {
        projectRepo.save(project);
    }

}
