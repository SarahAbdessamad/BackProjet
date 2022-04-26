package com.example.backprojet.model;


import javax.persistence.*;

@Entity
public class SubTaskMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Task parentTask;

    @ManyToOne
    private Task subTask;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Task getParentTask() {
        return parentTask;
    }

    public void setParentTask(Task parentTask) {
        this.parentTask = parentTask;
    }

    public Task getSubTask() {
        return subTask;
    }

    public void setSubTask(Task subTask) {
        this.subTask = subTask;
    }

    public SubTaskMapping() {
    }

    public SubTaskMapping(Task parentTask, Task subTask) {
        this.parentTask = parentTask;
        this.subTask = subTask;
    }
}
