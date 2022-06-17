package com.example.backprojet.controllor;

import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.Project;
import com.example.backprojet.model.Users;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/project")
public class ProjectController {
    private Project project;
    private ProjectService projectService;

    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private UsersRepo usersRepo;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PreAuthorize("hasRole('ProjectManager')")
    @PostMapping("/addproject")
    void addProject(@RequestBody Project project) {
        projectRepo.save(project);

    }
    @RequestMapping("/find/{id}")
    public Optional<Project> getProjectById(@PathVariable(value = "id") Long ProjectId) {
        return projectRepo.findById(ProjectId);
    }

    @PreAuthorize("hasRole('ProjectManager')")
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

    /*
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

     */
    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Project> updateProjectbyId(@PathVariable Long id, @RequestBody Project project) {
        Project project1 = projectRepo.findById(id).orElseThrow(() -> new UsernotFoundException("Project by id " + id + "was not found"));
        ;
        project1.setProjectTitle(project.getProjectTitle());
        project1.setProjectDescription(project.getProjectDescription());
        project1.setProjectDepartement(project.getProjectDepartement());
        project1.setProjectStartdate(project.getProjectStartdate());
        project1.setProjectDeadline(project.getProjectDeadline());
        project1.setProjectLocation(project.getProjectLocation());
        project1.setEffort_Days(project.getEffort_Days());
        Project updatedProject = projectRepo.save(project1);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);

    }

    @PutMapping("/{pprojectId}/delete/{userid}")
    Project deleteUsers(
            @PathVariable String userid,
            @PathVariable Long pprojectId
    ) {
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);

        List<Users> users  =  usersRepo.getUserByname(userid);
        Users user = users.get(0);
        System.out.println(user);
        project.deleteUsers(user);
        System.out.println(project);
        return projectRepo.save(project);
    }


    @PutMapping("/{pprojectId}/deleteAll")
    Project deleteAllUsers(
            @PathVariable Long pprojectId
    ) {
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);

        project.deleteAllUsers();
        System.out.println(project);
        return projectRepo.save(project);
    }

    @PutMapping("/{pprojectId}/enroll/{userid}")
    Project enrollUserToProject(
            @PathVariable String userid,
            @PathVariable Long pprojectId
    ) {
        Project project = projectRepo.findById(pprojectId).get();
        System.out.println(project);
        List<Users> users  =  usersRepo.getUserByname(userid);
        Users user = users.get(0);
        System.out.println(user);
        project.enrollUsers(user);
        System.out.println(project);
        return projectRepo.save(project);
    }

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
            @PathVariable String speciality,
            @PathVariable Long pprojectId
    ) {
        List<Users> enrolled_users = this.GetParticipants(pprojectId);
        List<Users> filtred_users = new ArrayList<>();
        Iterator<Users> i = enrolled_users.iterator();
        System.out.println("etape 0");
        System.out.println(filtred_users);
        while (i.hasNext()) {
            Users i2 = i.next();
            if (Objects.equals(i2.getSkill(), speciality)) {

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

    @RequestMapping("/findTitle/{ProjectTitle}")
    public List<Project> getProjectByProjectTitle(@PathVariable String ProjectTitle) {
        return (List<Project>) projectRepo.getProjectByProjectTitle(ProjectTitle);
    }

    @RequestMapping("/findProjectManager/{ProjectManager}")
    public List<Project> getProjectByProjectManager(@PathVariable String ProjectManager) {
        return (List<Project>) projectRepo.getProjectByProjectManager(ProjectManager);
    }
    @RequestMapping("/findLocation/{ProjectLocation}")
    public List<Project> getProjectByProjectLocation(@PathVariable String ProjectLocation) {
        return (List<Project>) projectRepo.getProjectByProjectLocation(ProjectLocation);
    }

    /*@PutMapping("/archive/{ProjectId}")
    public  ResponseEntity<Project> archived(@PathVariable(value = "id") Long ProjectId) {
        Project project1 = projectRepo.findById(ProjectId).orElseThrow(()-> new UsernotFoundException("Project by id "+ ProjectId + "was not found"));;
        System.out.println(project1);
        project1.setProjectStatus("archived");
        System.out.println(project1.getProjectStatus());
        Project updatedProject = projectRepo.save(project1);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }*/

    @PutMapping("/archived/{id}")
    public void updateProjects(@PathVariable(value = "id") Long ProjectId, @RequestBody Project project) {
        if (Objects.equals(project.getProjectStatus(), "archived")) {
            project.setProjectStatus("done");
        } else {
            project.setProjectStatus("archived");
        }
        projectRepo.save(project);
    }

    /*
        @PutMapping("/unarchived/{id}")
        public void unarchiveProjects (@PathVariable(value = "id") Long ProjectId, @RequestBody Project project) {

                project.setProjectStatus("in progress");
            projectRepo.save(project);
        }

        @PutMapping("/markdone/{id}")
        public void markDone (@PathVariable(value = "id") Long ProjectId, @RequestBody Project project) {
            project.setProjectStatus("done");
            projectRepo.save(project);
        }

     */
    @PutMapping("/ProjectStatus/{id}/{ProjectStatus}")
    public Project markStatus(@PathVariable(value = "id") Long ProjectId, @PathVariable(value = "ProjectStatus") String ProjectStatus) {
        Project project = projectRepo.findById(ProjectId).get();
        project.setProjectStatus(ProjectStatus);
        projectRepo.save(project);
        return project;
    }

    @RequestMapping("/findClient/{Client}")
    public List<Project> getProjectByClient(@PathVariable String Client) {
        return (List<Project>) projectRepo.getProjectByClient(Client);
    }

    @RequestMapping("/findProjectStatus/{ProjectStatus}")
    public List<Project> getProjectByProjectStatus(@PathVariable String ProjectStatus) {
        return (List<Project>) projectRepo.getProjectByProjectStatus(ProjectStatus);
    }

    @GetMapping("/unarchived")
    public ResponseEntity<List<Project>> getUnarchivedProjects() {
        List<Project> project = (List<Project>) projectRepo.getUnarchivedProject();
        return new ResponseEntity<List<Project>>(project, HttpStatus.OK);
    }
}
