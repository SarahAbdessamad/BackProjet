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

    @OneToMany(mappedBy = "project" , cascade = CascadeType.REMOVE)
    private List<Epic> epics;


    public List<Epic> getEpics() {
        return epics;
    }
    public void setEpics(List<Task> tasks) {
        this.epics = epics;
    }


    @Column(length = 2048)
    private String ProjectTitle;
    @Column(length = 2048)
    private String ProjectDescription;
    private String  ProjectDepartement ;
    private String ProjectStartdate;
    private String ProjectDeadline;
    private String ProjectStatus;
    private String ProjectLocation;
    private long Effort_Days;
    private boolean Favorite;
    private String Client;

    public boolean isFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean favorite) {
        this.Favorite = favorite;
    }



    public String getClient() {
        return Client;
    }

    public void setClient(String client) {
        Client = client;
    }

    @ManyToMany
    @JoinTable(
            name="users_enrolled",
            joinColumns = @JoinColumn(name="ProjectId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private List<Users> enrolledusers = new ArrayList<>();


    public Project(Long projectId, String projectTitle, String projectDescription, String projectDepartement, String projectStartdate, String projectDeadline, String projectStatus, String projectLocation, long effort_Days, boolean favorite, String client) {
        ProjectId = projectId;
        ProjectTitle = projectTitle;
        ProjectDescription = projectDescription;
        ProjectDepartement = projectDepartement;
        ProjectStartdate = projectStartdate;
        ProjectDeadline = projectDeadline;
        ProjectStatus = projectStatus;
        ProjectLocation = projectLocation;
        Effort_Days = effort_Days;
        Favorite = favorite;
        Client = client;
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

    public String getProjectLocation() {
        return ProjectLocation;
    }

    public void setProjectLocation(String projectLocation) {
        ProjectLocation = projectLocation;
    }

    public long getEffort_Days() {
        return Effort_Days;
    }

    public void setEffort_Days(long effort_Days) {
        Effort_Days = effort_Days;
    }


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
                ", ProjectLocation='" + ProjectLocation + '\'' +
                ", Effort_Days=" + Effort_Days +
                ", Favorite=" + Favorite +
                ", Client='" + Client + '\'' +
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
