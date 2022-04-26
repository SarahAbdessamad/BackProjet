package com.example.backprojet.controllor;

import com.example.backprojet.dto.TaskCreationForm;
import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.SubTaskMapping;
import com.example.backprojet.model.Task;
import com.example.backprojet.model.Users;
import com.example.backprojet.repo.SubTaskMappingRepo;
import com.example.backprojet.repo.TaskRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.SubTaskMappingService;
import com.example.backprojet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/subtask")
public class SubTaskController {

    private Task task;
    private TaskService taskService;

    @Autowired
    private TaskRepo taskRepo;
    private SubTaskMappingRepo subTaskMappingRepo;

    @Autowired
    SubTaskMappingService subTaskMappingService;

    public SubTaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PutMapping("/{taskId}/subtasks/change")
    public void subTasksCrudTest1() {
        // écrasement
        /*
         * remplacer la liste ancienne avec la nouvelle liste passée en paramètre
         */
    }


    @PutMapping("/{taskId}/subtasks/add")
    public void subTasksCrudTest2(@PathVariable("taskId") Long taskId, @RequestBody List<Long> subTaskIdList) {
        // écrasement
        Task t = taskRepo.getById(taskId);

        /*
         * ajoute à la loste existante des nouvelles entités
         */
        List<Task> newSubTaskList = new ArrayList<>();
        subTaskIdList.forEach(id -> newSubTaskList.add(taskRepo.findById(id).get()));

        t.getSubTasks().addAll(newSubTaskList);
        taskRepo.save(t);
    }


    @PutMapping("/{taskId}/subtasks/delete")
    public void subTasksCrudTest3() {
        // écrasement
        /*
         * vide la liste existante
         */
    }


    @PutMapping("/{taskId}/subtasks/")
    public void subTasksCrudTest4(@PathVariable(value = "id") Long TaskId) {
        // écrasement
        Task t = taskRepo.getById(TaskId);

        t.setSubTasks(new ArrayList());
        taskRepo.save(t);
    }


    @PutMapping("/{taskId}/seleteAllSubTasks/")
    public void subTasksCrudTest5(@PathVariable(value = "id") Long TaskId) {
        // écrasement

        Task t = taskRepo.getById(TaskId);

       /*   for( Task subTask : t.getSubTas ks() ){
              subTaskMappingRepo.delete(subTask);
          }

        */

    }
}
