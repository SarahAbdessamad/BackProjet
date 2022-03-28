package com.example.backprojet.controllor;


import com.example.backprojet.model.Task;
import com.example.backprojet.model.Users;
import com.example.backprojet.repo.TaskRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updateTask = taskService.updateTask(task);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }


    /*
    @PutMapping("/update")
    public ResponseEntity<Task> updateTaskbyId(@PathVariable Long id , @RequestBody Task task){
        Task task = taskRepo.findById(id);
        task.setTaskId(task.getTaskId());
        task.setTitle(task.getTitle());
        task.setDescription(task.getDescription());
        task.setSpeciality(task.getSpeciality());
        task.setPriority(task.getPriority());
        task.setStartdate(task.getStartdate());
        task.setDeadline(task.getDeadline());
        task.setMaxStart(task.getMaxStart());
        task.setMaxFinish(task.getMaxFinish());
        task.setProjectId(task.getProjectId());
        Task updatedGadget = taskRepo.save(task);
        return new ResponseEntity<>(updatedGadget, HttpStatus.OK);

    }

     */
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
