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
    private String departement;
    private int experience;
    private String post;

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSpeciality() {
        return skill;
    }

    public void setSpeciality(String speciality) {
        this.skill = speciality;
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
    @ManyToMany(mappedBy = "enrolledusers")
    private Set<Project> listprojects = new HashSet<>();

    @JsonIgnore
    @ManyToMany(mappedBy = "enrollUsersToTask")
    private Set<Task> listTasks = new HashSet<>();

    public Users() {
        this.id = Long.valueOf(0);
        this.nom = "";
        this.prenom = "";
    }

    public Users(Long id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = Users.this.prenom;
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

    public String getprenom() {
        return prenom;
    }

    public void setprenom(String prenom) {
        this.prenom = Users.this.prenom;
    }

    public Set<Project> getListprojects() {
        return listprojects;
    }

    public Set<Task> getListTasks() {
        return listTasks;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", speciality='" + skill + '\'' +
                ", departement='" + departement + '\'' +
                ", experience=" + experience +
                ", post='" + post + '\'' +
                '}';
    }
}
