package com.example.backprojet.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dependency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)

    private Long id;
    private String IdOfTheTask;
    private String Status;

    @JsonIgnore
    @ManyToMany(mappedBy = "enrollDependenciesToTask")
    private Set<Task> listTasks = new HashSet<>();



    public Dependency() {

    }

    public Dependency(Long id, String idOfTheTask, String status) {
        this.id = id;
        IdOfTheTask = idOfTheTask;
        Status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Task> getListTasks() {
        return listTasks;
    }

    public String getIdOfTheTask() {
        return IdOfTheTask;
    }

    public void setIdOfTheTask(String idOfTheTask) {
        IdOfTheTask = idOfTheTask;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "id=" + id +
                ", IdOfTheTask='" + IdOfTheTask + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
