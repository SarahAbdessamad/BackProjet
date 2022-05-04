package com.example.backprojet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.catalina.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long ProjectId;


/*
    @JsonIgnore
    @OneToMany(mappedBy="project")
    private Set<Task> tasks =new HashSet<>();

 */

    @Column(length = 2048)
    private String ProjectTitle;
    @Column(length = 2048)
    private String ProjectDescription;
    private String  ProjectDepartement ;
    private String ProjectStartdate;
    private String ProjectDeadline;
    private String ProjectStatus;




    @ManyToMany
    @JoinTable(
            name="users_enrolled",
            joinColumns = @JoinColumn(name="ProjectId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Users> enrolledusers = new ArrayList<>();


    public Project(Long projectId, String projectTitle, String projectDescription, String projectDepartement, String projectStartdate, String projectDeadline, String projectStatus) {
        ProjectId = projectId;
        ProjectTitle = projectTitle;
        ProjectDescription = projectDescription;
        ProjectDepartement = projectDepartement;
        ProjectStartdate = projectStartdate;
        ProjectDeadline = projectDeadline;
        ProjectStatus = projectStatus;
    }

    public Project() {
    }

    public Long getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Long projectId) {
        ProjectId = projectId;
    }

    public String getProjectTitle() {
        return ProjectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        ProjectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return ProjectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        ProjectDescription = projectDescription;
    }

    public String getProjectDepartement() {
        return ProjectDepartement;
    }

    public void setProjectDepartement(String projectDepartement) {
        ProjectDepartement = projectDepartement;
    }

    public String getProjectStartdate() {
        return ProjectStartdate;
    }

    public void setProjectStartdate(String projectStartdate) {
        ProjectStartdate = projectStartdate;
    }

    public String getProjectDeadline() {
        return ProjectDeadline;
    }

    public void setProjectDeadline(String projectDeadline) {
        ProjectDeadline = projectDeadline;
    }

    public String getProjectStatus() {
        return ProjectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        ProjectStatus = projectStatus;
    }
    /*
    public Set<Task> getTasks() {
        return tasks;
    }

 */



    public List<Users> getEnrolledusers() {
        return enrolledusers;
    }
    @Override
    public String toString() {
        return "Project{" +
                "ProjectId=" + ProjectId +
                ", ProjectTitle='" + ProjectTitle + '\'' +
                ", ProjectDescription='" + ProjectDescription + '\'' +
                ", ProjectDepartement='" + ProjectDepartement + '\'' +
                ", ProjectStartdate='" + ProjectStartdate + '\'' +
                ", ProjectDeadline='" + ProjectDeadline + '\'' +
                ", ProjectStatus='" + ProjectStatus + '\'' +
                '}';
    }

    public void enrollUsers(Users user) {
        enrolledusers.add(user);
    }
    public void deleteUsers(Users user) { enrolledusers.remove(user);}
    public void deleteAllUsers() { enrolledusers.clear();}
    //public void getUsersByProject(Users user) {enrolledusers.findAll(User);}
    public List <Users> specialityOfUser(Users user) {
        List <Users> users=new ArrayList<Users>();
        for(int i=0;i<enrolledusers.size();i++)
            users.add(enrolledusers.get(i));
        return users;
    }
}
