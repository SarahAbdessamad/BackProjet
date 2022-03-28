package com.example.backprojet.controllor;


import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.Task;
import com.example.backprojet.model.Users;
import com.example.backprojet.repo.TaskRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class TaskController {
    private Task task;
    private TaskService taskService;

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UsersRepo usersRepo;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/addtask")
    Task addTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }

    @RequestMapping("/find/{id}")
    public Optional<Task> getTaskById(@PathVariable(value = "id") Long TaskId) {
        return taskRepo.findById(TaskId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> task = taskRepo.findAll();
        return new ResponseEntity<List<Task>>(task, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{TaskId}")
    public ResponseEntity<?> deleteProject(@PathVariable("TaskId") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{taskId}/enroll/{userid}")
    Task enrollUsersToTask(
            @PathVariable Long userid,
            @PathVariable Long taskId
    ) {
        Task task = taskRepo.findById(taskId).get();
        System.out.println(task);
        Users user = usersRepo.findById(userid).get();
        System.out.println(user);
        task.enrollUsersToTask(user);
        System.out.println(task);
        return taskRepo.save(task);

    }

    @PutMapping("/{taskId}/delete/{userid}")
    Task deleteUsersFromTask(
            @PathVariable Long userid,
            @PathVariable Long taskId
    ) {
        Task task = taskRepo.findById(taskId).get();
        System.out.println(task);
        Users user = usersRepo.findById(userid).get();
        System.out.println(user);
        task.deleteUsersFromTask(user);
        System.out.println(task);
        return taskRepo.save(task);
    }

    @PutMapping("/{taskId}/deleteAll")
    Task deleteAllUsers(
            @PathVariable Long taskId
    ) {
        Task task = taskRepo.findById(taskId).get();
        System.out.println(task);
        task.deleteAllUsersFromTask();
        System.out.println(task);
        return taskRepo.save(task);
    }

    @RequestMapping("/findTask/{projectId}")
    public List<Task> getTaskByProject(@PathVariable String projectId) {

        return (List<Task>) taskRepo.getTaskByProject(projectId);
    }

    @PutMapping("/updateByID/{id}")

    public ResponseEntity<Task> updateGadgetbyId(@PathVariable Long id , @RequestBody Task task){
        Task task1 = taskRepo.findById(id).orElseThrow(()-> new UsernotFoundException("User by id "+ id + "was not found"));;
        task1.setTitle(task.getTitle());
        task1.setDescription(task.getDescription());
        task1.setSpeciality(task.getSpeciality());
        task1.setPriority(task.getPriority());
        task1.setStartdate(task.getStartdate());
        task1.setDeadline(task.getDeadline());
        task1.setMaxStart(task.getMaxStart());
        task1.setMaxFinish(task.getMaxFinish());
        task1.setProjectId(task.getProjectId());
        Task updatedGadget = taskRepo.save(task1);
        return new ResponseEntity<>(updatedGadget, HttpStatus.OK);

    }





    /*
    @PutMapping("/{taskId}/enroll/{ProjectId}")
    Task assign(
            @PathVariable  Long userid,
            @PathVariable  Long taskId
    ){
        Task task = taskRepo.findById(taskId).get();
        System.out.println(task);
        Users user = usersRepo.findById(userid).get();
        System.out.println(user);
        task.enrollUsersToTask(user);
        System.out.println(task);
        return taskRepo.save(task);
    }
     */

}
