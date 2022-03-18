package com.example.backprojet.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Participants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long participantsId ;
    private Long idUser;
    private Long idProject;

    @OneToOne
    private Project project;
    @OneToMany
    private List<Users> users;


    public Participants(){

    }

    public Participants(Long idParticipants, Long idUser) {
        this.participantsId = idParticipants;
        this.idUser = idUser;
        this.idProject=idProject;
    }

    public Long getIdParticipants() {
        return participantsId;
    }

    public void setIdParticipants(Long idParticipants) {
        this.participantsId = idParticipants;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    @Override
    public String toString() {
        return "Participants{" +
                "participantsId=" + participantsId +
                ", idUser=" + idUser +
                ", idProject=" + idProject +
                ", project=" + project +
                ", users=" + users +
                '}';
    }
}
