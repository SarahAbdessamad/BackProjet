package com.example.backprojet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)


    private Long IdNote;
    private String NoteAuthor ;
    private String NoteDate ;
    private String NoteBody ;
    private long ProjectId;

    @ManyToOne
    @JsonIgnore
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }

    //private String userName;
    public Note(Long idNote, String noteAuthor, String noteDate, String noteBody) {
        IdNote = idNote;
        NoteAuthor = noteAuthor;
        NoteDate = noteDate;
        NoteBody = noteBody;
    }

/*
    @ManyToOne
    @JsonIgnore
    private Project project;
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public long getProjectId() {
        return ProjectId;
    }
    public void setProjectId(long projectId) {
        ProjectId = projectId;
    }

 */

    public Note() {
    }

    public Long getIdNote() {
        return IdNote;
    }

    public void setIdNote(Long idNote) {
        IdNote = idNote;
    }

    public String getNoteAuthor() {
        return NoteAuthor;
    }

    public void setNoteAuthor(String noteAuthor) {
        NoteAuthor = noteAuthor;
    }

    public String getNoteDate() {
        return NoteDate;
    }

    public void setNoteDate(String noteDate) {
        NoteDate = noteDate;
    }

    public String getNoteBody() {
        return NoteBody;
    }

    public void setNoteBody(String noteBody) {
        NoteBody = noteBody;
    }

    public long getProjectId() {
        return ProjectId;
    }

    public void setProjectId(long projectId) {
        ProjectId = projectId;
    }

/*
    public String getUserName() {
        return userName;
    }

 */
/*
    public void setUserName(String userName) {
        this.userName = userName;
    }

 */

    @Override
    public String toString() {
        return "Note{" +
                "IdNote=" + IdNote +
                ", NoteAuthor='" + NoteAuthor + '\'' +
                ", NoteDate='" + NoteDate + '\'' +
                ", NoteBody='" + NoteBody + '\'' +
                //", userName='" + userName + '\'' +

                '}';
    }
}
