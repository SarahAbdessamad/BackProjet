package com.example.backprojet.controllor;


import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.Epic;
import com.example.backprojet.model.Project;
import com.example.backprojet.repo.EpicRepo;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.service.EpicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/epic")

public class EpicController {

    private Epic epic;
    @Autowired
    private EpicRepo epicRepo;
    @Autowired
    private ProjectRepo projectRepo;
    @Autowired
    private  EpicService epicService;

    public EpicController(EpicService epicService) {
        this.epicService = epicService;
    }

    @PostMapping("/addepic")
    void addEpic(@RequestBody Epic epic) {
        System.out.println("epic");
        Project epicProject  = projectRepo.getById(epic.getProjectId());
        epic.setProject(epicProject);
        epicRepo.save(epic);
    }

    @RequestMapping("/find/{id}")
    public Optional<Epic> getEpicById(@PathVariable(value = "id") Long EpicId) {
        return epicRepo.findById(EpicId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Epic>> getAllEpic() {
        List<Epic> epic = epicRepo.findAll();
        return new ResponseEntity<>(epic, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{EpicId}")
    public ResponseEntity<?> deleteEpic(@PathVariable("EpicId") Long EpicId) {
        epicService.deleteEpic(EpicId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Epic> updateEpicbyId(@PathVariable Long id, @RequestBody Epic epic) {
        Epic epic1 = epicRepo.findById(id).orElseThrow(() -> new UsernotFoundException("Epic by id " + id + "was not found"));
        epic1.setEpicTitle(epic.getEpicTitle());
        epic1.setEpicDescription(epic.getEpicDescription());
        epic1.setEpicStartdate(epic.getEpicStartdate());
        epic1.setEpicDeadline(epic.getEpicDeadline());
        epic1.setEffort_Days(epic.getEffort_Days());
        Epic updatedEpic = epicRepo.save(epic1);
        return new ResponseEntity<>(updatedEpic, HttpStatus.OK);
    }

    @RequestMapping("/findepic/{projectId}")
    public List<Epic> getEpicByProject(@PathVariable String projectId) {
        return  epicRepo.getEpicByProject(projectId);
    }

    @RequestMapping("/findTitle/{EpicTitle}")
    public List<Epic> getEpicByEpicTitle(@PathVariable String EpicTitle) {
        return (List<Epic>) epicRepo.getEpicByEpicTitle(EpicTitle);
    }




}
