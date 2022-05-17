package com.example.backprojet.model;


import javax.persistence.*;

@Entity
public class Epic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EpicId", nullable = false)
    private Long EpicId;

    @Column(length = 2048)
    private String EpicTitle;
    @Column(length = 2048)
    private String EpicDescription;
    private String EpicStartdate;
    private String EpicDeadline;
    private long Effort_Days;
    private String ProjectId;

    public Epic() {
    }


    public Long getEpicId() {
        return EpicId;
    }

    public void setEpicId(Long epicId) {
        EpicId = epicId;
    }

    public String getEpicTitle() {
        return EpicTitle;
    }

    public void setEpicTitle(String epicTitle) {
        EpicTitle = epicTitle;
    }

    public String getEpicDescription() {
        return EpicDescription;
    }

    public void setEpicDescription(String epicDescription) {
        EpicDescription = epicDescription;
    }



    public String getEpicStartdate() {
        return EpicStartdate;
    }

    public void setEpicStartdate(String epicStartdate) {
        EpicStartdate = epicStartdate;
    }

    public String getEpicDeadline() {
        return EpicDeadline;
    }

    public void setEpicDeadline(String epicDeadline) {
        EpicDeadline = epicDeadline;
    }



    public long getEffort_Days() {
        return Effort_Days;
    }

    public void setEffort_Days(long effort_Days) {
        Effort_Days = effort_Days;
    }


    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public Epic(Long epicId, String epicTitle, String epicDescription, String projectId) {
        EpicId = epicId;
        EpicTitle = epicTitle;
        EpicDescription = epicDescription;
        ProjectId = projectId;
    }

    public Epic(Long epicId, String epicTitle, String epicDescription, String epicStartdate, String epicDeadline, long effort_Days, String projectId) {
        EpicId = epicId;
        EpicTitle = epicTitle;
        EpicDescription = epicDescription;
        EpicStartdate = epicStartdate;
        EpicDeadline = epicDeadline;
        Effort_Days = effort_Days;
        ProjectId = projectId;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "EpicId=" + EpicId +
                ", EpicTitle='" + EpicTitle + '\'' +
                ", EpicDescription='" + EpicDescription + '\'' +
                ", EpicStartdate='" + EpicStartdate + '\'' +
                ", EpicDeadline='" + EpicDeadline + '\'' +
                ", Effort_Days=" + Effort_Days +
                ", ProjectId='" + ProjectId + '\'' +
                '}';
    }
}