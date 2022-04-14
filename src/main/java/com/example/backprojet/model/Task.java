package com.example.backprojet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long TaskId;
    @Column(length = 2048)
    private String title;
    @Column(length = 2048)
    private String description;
    private String speciality;
    private String Priority;
    private String Startdate;
    private String Deadline;
    private String MaxStart;
    private String MaxFinish;
    private String projectId;

    @Transient
    private List<Task> subTasks;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public List<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(List<Task> subTasks) {
        this.subTasks = subTasks;
    }

    @ManyToMany
    @JoinTable(
            name = "enroll_users",
            joinColumns = @JoinColumn(name = "TaskId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Users> enrollUsersToTask = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "enroll_Dependencies",
            joinColumns = @JoinColumn(name = "TaskId"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Dependency> enrollDependenciesToTask = new HashSet<>();

/*
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="ProjectId", referencedColumnName = "TaskId")
    private Project project;

 */
    /*@ManyToMany
    @JoinTable(
            name="tasks_enrolled",
            joinColumns = @JoinColumn(name="TaskId"),
            inverseJoinColumns = @JoinColumn(name = "TaskId")
    )
    private List<Task> enrolledtasks = new ArrayList<>();*/

    /*@JsonIgnore
    @ManyToMany(mappedBy = "enrolledtasks")
    private Set<Task> listOfTasks = new HashSet<>();*/


    public Task(Long taskId, String title, String description, String speciality, String priority, String startdate, String deadline, String maxStart, String maxFinish) {
        TaskId = taskId;
        this.title = title;
        this.description = description;
        this.speciality = speciality;
        Priority = priority;
        Startdate = startdate;
        Deadline = deadline;
        MaxStart = maxStart;
        MaxFinish = maxFinish;
    }

    public Task() {

    }

    public Long getTaskId() {
        return TaskId;
    }

    public void setTaskId(Long taskId) {
        TaskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public String getStartdate() {
        return Startdate;
    }

    public void setStartdate(String startdate) {
        Startdate = startdate;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getMaxStart() {
        return MaxStart;
    }

    public void setMaxStart(String maxStart) {
        MaxStart = maxStart;
    }

    public String getMaxFinish() {
        return MaxFinish;
    }

    public void setMaxFinish(String maxFinish) {
        MaxFinish = maxFinish;
    }

    public Set<Users> getEnrollUsersToTask() {
        return enrollUsersToTask;
    }

    public Set<Dependency> getEnrollDependenciesToTask() {
        return enrollDependenciesToTask;
    }

    /*public Set<Task> getListTasks() {
        return listOfTasks;
    }*/


/*
    public Project getProject() {
        return project;
    }

 */

    @Override
    public String toString() {
        return "Task{" +
                "TaskId=" + TaskId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", speciality='" + speciality + '\'' +
                ", Priority='" + Priority + '\'' +
                ", Startdate='" + Startdate + '\'' +
                ", Deadline='" + Deadline + '\'' +
                ", MaxStart='" + MaxStart + '\'' +
                ", MaxFinish='" + MaxFinish + '\'' +
                '}';
    }

    public void enrollUsersToTask(Users user) {
        enrollUsersToTask.add(user);
    }

    public void deleteUsersFromTask(Users user) {
        enrollUsersToTask.remove(user);
    }

    public void deleteAllUsersFromTask() {
        enrollUsersToTask.clear();
    }

    public void enrollDependenciesToTask(Dependency dependency) {
        enrollDependenciesToTask.add(dependency);
    }

    /*public void enrolledtasks(Task task) { enrolledtasks.add(task);}
    public List<Task> getEnrolledtasks() {
        return enrolledtasks;
    }*/


}
