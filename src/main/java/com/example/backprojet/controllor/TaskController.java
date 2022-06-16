package com.example.backprojet.controllor;


import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.*;
import com.example.backprojet.service.repo.StoryRepo;
import com.example.backprojet.service.repo.TaskRepo;
import com.example.backprojet.service.repo.UsersRepo;
import com.example.backprojet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/task")
public class TaskController {

    private Task task;
    private TaskService taskService;

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UsersRepo usersRepo;
    @Autowired
    private StoryRepo storyRepo;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /*@PostMapping("/addtask")
    Task addTask(@RequestBody Task task) {
        return taskRepo.save(task);
    }*/

    @PostMapping("/addtask")
    Task addTask(@RequestBody Task task) {
        Story Taskstory  = storyRepo.getById(task.getStoryId());
        task.setStory( Taskstory );
       return  taskRepo.save(task);
    }

    @RequestMapping("/find/{id}")
    public Optional<Task> getTaskById(@PathVariable(value = "id") Long TaskId) {

        return taskRepo.findById(TaskId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTask() {
        List<Task> task = taskRepo.findAll();
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{TaskId}")
    public ResponseEntity<?> deletetask(@PathVariable("TaskId") Long id) {
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

    @RequestMapping("/findTask/{StoryId}")
    public List<Task> getTaskByProject(@PathVariable String StoryId) {

        return (List<Task>) taskRepo.getTaskByStory(StoryId);
    }

    @GetMapping("/{taskID}/get")
    Set<Users> GetParticipants(

            @PathVariable Long taskID
    ) {
        Task task = taskRepo.findById(taskID).get();
        System.out.println(task);
        Set<Users> enrolled_users = task.getEnrollUsersToTask();
        System.out.println(enrolled_users);
        return enrolled_users;
    }

    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Task> updatetaskbyId(@PathVariable Long id, @RequestBody Task task) {
        Task task1 = taskRepo.findById(id).orElseThrow(() -> new UsernotFoundException("User by id " + id + "was not found"));
        task1.setTitle(task.getTitle());
        task1.setDescription(task.getDescription());
        task1.setRequiredSkill(task.getRequiredSkill());
        task1.setPriority(task.getPriority());
        task1.setStartdate(task.getStartdate());
        task1.setDeadline(task.getDeadline());
        task1.setMaxStart(task.getMaxStart());
        task1.setMaxFinish(task.getMaxFinish());
        task1.setStoryId(task.getStoryId());
        task1.setAlmostfinished(task.isAlmostfinished());
        task1.setBlocked(task.isBlocked());
        task1.setUrgent(task.isUrgent());
        task1.setProgress(task.getProgress());
        Task updatedGadget = taskRepo.save(task1);
        return new ResponseEntity<>(updatedGadget, HttpStatus.OK);
    }

    /*
    @PutMapping("/{taskId}/enroll/{storiesId}")
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


    /*@PutMapping("/{taskId}/Dependency/{id}")
    Task enrolledtasks(
            @PathVariable Long taskId,
            @PathVariable Long id
    ) {
        Task task = taskRepo.findById(taskId).get();
        System.out.println(task);
        Task task1 = taskRepo.findById(id).get();
        System.out.println(task1);
        // task.enrolledtasks(task1);
        System.out.println(task);
        return taskRepo.save(task);
    }*/

    @PutMapping("/updateStatus/{id}/{status}")
    public void updateStatus(@PathVariable Long id,@PathVariable String status) {
        Task task2 = taskRepo.findById(id).orElseThrow(() -> new UsernotFoundException("User by id " + id + "was not found"));
        task2.setStatus(status);
        taskRepo.save(task2);
    }
    /*
    @GetMapping("/all")
    public ResponseEntity<List<Project>> getAllBlocked() {
        List<Task> task = taskRepo.findAll();
        return new ResponseEntity<List<Task>>(task, HttpStatus.OK);
    }*/



}
