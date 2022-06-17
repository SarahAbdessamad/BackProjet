package com.example.backprojet.service;


import com.example.backprojet.model.Task;
import com.example.backprojet.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TaskService {
    private TaskRepo taskRepo;
    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }
    public void deleteTask(Long TaskId){
        taskRepo.deleteById(TaskId);
    }
    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }

}
