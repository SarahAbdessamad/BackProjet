package com.example.backprojet.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Users implements Serializable {

    @Id
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String departement;
    private int experience;
    private String post;

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    private long mobile;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    //private String skill;
    @ElementCollection
    //@Value("${arrayOfStrings}").split(',')}"
    //@CollectionTable(name = "skill")
    private List<String> skill = new ArrayList<String>();
    @ElementCollection
    private List<Long> participatedInThisProject = new ArrayList<>();
/*
    @OneToMany(mappedBy = "users" , cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Note> notes = new ArrayList<>();
    public List<Note> getNotes() {
        return notes;
    }
    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

 */

    @OneToMany(mappedBy = "userss" , cascade = CascadeType.REMOVE)
    private List<Vacation> vacations;

    /*
        public List<Vacation> getVacations() {
            return vacations;
        }
        public void setVacations(List<Vacation> vacations) {
            this.vacations = vacations;
        }

     */
    @ManyToOne
    @JoinColumn(name = "project_project_id")
    private Project project;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )

    private Set<Role> role;

    @OneToMany(mappedBy = "user" , cascade = CascadeType.REMOVE)
    private List<Project> projects;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }


    public List<String> getSkill() {
        return skill;
    }

    public void setSkill(List<String> skill) {
        this.skill = skill;
    }



    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }



    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledusers" , cascade = CascadeType.REMOVE)
    private Set<Project> listprojects = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "enrollUsersToTask")
    private Set<Task> listTasks = new HashSet<>();

    public Project getProject() {
        return project;
    }


    public Users() {

    }



    public Users(String userName, String userFirstName, String userLastName, String userPassword, String departement, int experience, String post, String skill) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.departement = departement;
        this.experience = experience;
        this.post = post;
        this.skill = Collections.singletonList(skill);
    }

    public Users(String userName, String userFirstName, String userLastName, String userPassword) {
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", userFirstName='" + userFirstName + '\'' +
                ", userLastName='" + userLastName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", departement='" + departement + '\'' +
                ", experience=" + experience +
                ", post='" + post + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }

    public Set<Project> getListprojects() {
        return listprojects;
    }

    public Set<Task> getListTasks() {
        return listTasks;
    }

    public List<Long> getParticipatedInThisProject() {
        return participatedInThisProject;
    }

    public void setParticipatedInThisProject(List<Long> participatedInThisProject) {
        this.participatedInThisProject = participatedInThisProject;
    }
}
