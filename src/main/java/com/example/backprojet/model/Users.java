package com.example.backprojet.model;



import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;
    private String nom;
    private String prénom;

    @OneToMany
    private List<Participants> listparticipants;

    public Users() {
        this.id = Long.valueOf(0);
        this.nom = "";
        this.prénom = "";
    }

    public Users(Long id, String nom, String prénom) {
        this.id = id;
        this.nom = nom;
        this.prénom = prénom;
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

    public String getPrénom() {
        return prénom;
    }

    public void setPrénom(String prénom) {
        this.prénom = prénom;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prénom='" + prénom + '\'' +
                '}';
    }
}
