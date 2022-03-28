package com.example.backprojet.controllor;

import com.example.backprojet.model.Project;
import com.example.backprojet.model.Users;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private Project project;
    private  ProjectService projectService;

    @Autowired
    private  ProjectRepo projectRepo;
    @Autowired
    private UsersRepo usersRepo;
        public ProjectController(ProjectService projectService){
        this.projectService=projectService;
        }

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

    @DeleteMapping("/delete/{ProjectId}")
    public ResponseEntity<?> deleteProject(@PathVariable("ProjectId") Long id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{pprojectId}/enroll/{userid}")
    Project enrollUsers(
            @PathVariable  Long userid,
            @PathVariable  Long pprojectId,@RequestBody Project project1
    ){
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);
        Users user = usersRepo.findById(userid).get();
        System.out.println(user);
        project.enrollUsers(user);
        System.out.println(project);
        return projectRepo.save(project);

    }

    @PutMapping("/{pprojectId}/delete/{userid}")
    Project deleteUsers(
            @PathVariable  Long userid,
            @PathVariable  Long pprojectId
    ){
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);
        Users user = usersRepo.findById(userid).get();
        System.out.println(user);
        project.deleteUsers(user);
        System.out.println(project);
        return projectRepo.save(project);
    }


    @PutMapping("/{pprojectId}/deleteAll")
    Project deleteAllUsers(
            @PathVariable  Long pprojectId
    ){
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);

        project.deleteAllUsers();
        System.out.println(project);
        return projectRepo.save(project);
    }
    /*
    @PutMapping("/{pprojectId}/enroll/{userid}")
    Project getUsersByProject(
            @PathVariable  Long userid,
            @PathVariable  Long pprojectId
    ){
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);
        Users user = usersRepo.findById(userid).get();
        System.out.println(user);
        project.enrollUsers(user);
        System.out.println(project);
        return projectRepo.save(project);
    }
     */
    @GetMapping("/{pprojectId}/get")
    List<Users> GetParticipants(

            @PathVariable Long pprojectId
    ) {
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);
        List<Users> enrolled_users = project.getEnrolledusers();
        System.out.println(enrolled_users);
        return enrolled_users;
    }
    @GetMapping("/{pprojectId}/getspec/{speciality}")
    List<Users> specialityOfUser(
            @PathVariable  String speciality,
            @PathVariable  Long pprojectId
    ){
        List<Users> enrolled_users = this.GetParticipants(pprojectId);
        List<Users>  filtred_users =  new ArrayList<>();
        Iterator<Users> i = enrolled_users.iterator();
        System.out.println("etape 0");
        System.out.println(filtred_users);
        while (i.hasNext()) {
            Users i2= i.next();
            if(Objects.equals(i2.getSpeciality(), speciality)) {

                System.out.println("etape 1");
                //System.out.println(i.next());
                System.out.println(i2);
                filtred_users.add(i2);
            }
        }
        System.out.println("etape final");
        System.out.println(filtred_users);
        return filtred_users;

    }
}
