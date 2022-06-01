package com.example.backprojet.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nom;
    private String prenom;
    private String skill;
    private String Role;
    private String email;
    private String password;

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", skill='" + skill + '\'' +
                ", Role='" + Role + '\'' +
                ", Email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", departement='" + departement + '\'' +
                ", experience=" + experience +
                ", post='" + post + '\'' +
                '}';
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    private String departement;
    private int experience;
    private String post;

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

    public Users() {
        this.id = Long.valueOf(0);
        this.nom = "";
        this.prenom = "";
    }

    public Users(Long id, String nom, String prenom, String skill, String role, String email, String password, String departement, int experience, String post) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.skill = skill;
        Role = role;
        email = email;
        this.password = password;
        this.departement = departement;
        this.experience = experience;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Set<Project> getListprojects() {
        return listprojects;
    }

    public Set<Task> getListTasks() {
        return listTasks;
    }



}
