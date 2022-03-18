package com.example.backprojet.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity

public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long ProjectId;
    private String ProjectTitle;
    private String ProjectDescription;
    private String  ProjectDepartement ;
    private String ProjectStartdate;
    private String ProjectDeadline;
    private Long participantsId;
    @OneToOne
    private Participants participants;

    public Project(Long projectId, String projectTitle, String projectDescription, String projectDepartement, String projectStartdate, String projectDeadline, long participantsId) {
        ProjectId = projectId;
        ProjectTitle = projectTitle;
        ProjectDescription = projectDescription;
        ProjectDepartement = projectDepartement;
        ProjectStartdate = projectStartdate;
        ProjectDeadline = projectDeadline;
        this.participantsId = participantsId;
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

    public long getParticipantsId() {
        return participantsId;
    }

    public void setParticipantsId(long participantsId) {
        this.participantsId = participantsId;
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
                ", participants=" + participantsId +
                '}';
    }
}
