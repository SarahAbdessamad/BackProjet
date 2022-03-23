package com.example.backprojet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String ProjectTitle;
    private String ProjectDescription;
    private String  ProjectDepartement ;
    private String ProjectStartdate;
    private String ProjectDeadline;



    @ManyToMany
    @JoinTable(
            name="users_enrolled",
            joinColumns = @JoinColumn(name="ProjectId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Users> enrolledusers = new HashSet<>();


    public Project(Long projectId, String projectTitle, String projectDescription, String projectDepartement, String projectStartdate, String projectDeadline) {
        ProjectId = projectId;
        ProjectTitle = projectTitle;
        ProjectDescription = projectDescription;
        ProjectDepartement = projectDepartement;
        ProjectStartdate = projectStartdate;
        ProjectDeadline = projectDeadline;
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
/*
    public Set<Task> getTasks() {
        return tasks;
    }

 */



    public Set<Users> getEnrolledusers() {
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
                '}';
    }

    public void enrollUsers(Users user) {
        enrolledusers.add(user);
    }
    public void deleteUsers(Users user) { enrolledusers.remove(user);}
    public void deleteAllUsers() { enrolledusers.clear();}


}
