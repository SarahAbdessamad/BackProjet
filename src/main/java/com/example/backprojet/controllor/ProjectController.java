package com.example.backprojet.controllor;

import com.example.backprojet.model.Participants;
import com.example.backprojet.model.Project;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping("/find/{id}")
    public Optional<Project> getProjectById (@PathVariable(value = "id") Long ProjectId) {
        return projectRepo.findById(ProjectId);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllProject() {
        List<Project> project = projectRepo.findAll();
        return new ResponseEntity<List<Project>>(project, HttpStatus.OK);
    }

}
