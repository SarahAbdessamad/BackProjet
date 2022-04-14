package com.example.backprojet.dto;

import com.example.backprojet.model.Task;

import java.util.List;

public class TaskCreationForm {

    private Task newTask;
    private List<Long> subTasksIdList;

    public Task getNewTask() {
        return newTask;
    }

    public void setNewTask(Task newTask) {
        this.newTask = newTask;
    }

    public List<Long> getSubTasksIdList() {
        return subTasksIdList;
    }

    public void setSubTasksIdList(List<Long> subTasksIdList) {
        this.subTasksIdList = subTasksIdList;
    }

    public TaskCreationForm() {
    }

    public TaskCreationForm(Task newTask, List<Long> subTasksIdList) {
        this.newTask = newTask;
        this.subTasksIdList = subTasksIdList;
    }
}
