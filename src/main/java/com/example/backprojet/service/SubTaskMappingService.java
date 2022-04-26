package com.example.backprojet.service;

import com.example.backprojet.model.SubTaskMapping;
import com.example.backprojet.model.Task;
import com.example.backprojet.repo.SubTaskMappingRepo;
import com.example.backprojet.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubTaskMappingService {

    @Autowired
    TaskRepo taskRepository;

    @Autowired
    SubTaskMappingRepo repoSubtask;


    @Autowired
    SubTaskMappingRepo subTaskMappingRepository;

    public List<Task> getSubTasks(Task task) {
        List<Task> subTasks = new ArrayList<>();
        List<SubTaskMapping> subTaskMappings = subTaskMappingRepository.findAllByParentTask(task);
        subTaskMappings.forEach(subTaskMapping -> {
            subTasks.add(subTaskMapping.getSubTask());
        });
        return subTasks;
    }

    public Task addSubTasks(Task task, List<Long> subTasksIdList) {
        List<Task> subTasks = new ArrayList<>();
        subTasksIdList.forEach(id -> {
            Task subTask = taskRepository.findById(id).get();
            subTasks.add(subTask);
            subTaskMappingRepository.save(new SubTaskMapping(task, subTask));
        });
        task.setSubTasks(subTasks);
        return task;
    }
}