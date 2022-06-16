package com.example.backprojet.controllor;


import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.Epic;
import com.example.backprojet.model.Story;
import com.example.backprojet.service.repo.EpicRepo;
import com.example.backprojet.service.repo.StoryRepo;
import com.example.backprojet.service.repo.TaskRepo;
import com.example.backprojet.service.StoryService;
import com.example.backprojet.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/story")
public class StoryController {

    private Story story;
    @Autowired
    private StoryRepo storyRepo;
    @Autowired
    private TaskRepo taskRepo;

    @Autowired
    EpicRepo epicRepo;

    @Autowired
    private StoryService storyService;
    @Autowired
    private TaskService taskService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }


    @PostMapping("/addStory")
    void addStory(@RequestBody Story story) {
        Epic storyEpic  = epicRepo.getById(story.getEpicId());
        story.setEpic( storyEpic );
        storyRepo.save(story);
    }


    @RequestMapping("/find/{id}")
    public Optional<Story> getStoryById(@PathVariable(value = "id") Long StoryId) {
        return storyRepo.findById(StoryId);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Story>> getAllStory() {
        List<Story> story = storyRepo.findAll();
        return new ResponseEntity<>(story, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStory(@PathVariable("id") Long StoryId) {
        storyService.deleteStory(StoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Story> updateStorybyId(@PathVariable Long id, @RequestBody Story story) {
        Story story1 = storyRepo.findById(id).orElseThrow(() -> new UsernotFoundException("Story by id " + id + "was not found"));
        story1.setStoryTitle(story.getStoryTitle());
        story1.setStoryDescription(story.getStoryDescription());
        story1.setStoryStartdate(story.getStoryStartdate());
        story1.setStoryDeadline(story.getStoryDeadline());
        story1.setStoryStatus(story.getStoryStatus());
        story1.setPriority(story.getPriority());
        story1.setEpicId(story.getEpicId());
        Story updatedStory = storyRepo.save(story1);
        return new ResponseEntity<>(updatedStory, HttpStatus.OK);
    }

    @RequestMapping("/findTitle/{StoryTitle}")
    public List<Story> getStoryByStoryTitle(@PathVariable String StoryTitle) {
        return (List<Story>) storyRepo.getStoryByStoryTitle(StoryTitle);
    }

    @RequestMapping("/findstory/{EpicId}")
    public List<Story> getStoryByEpic(@PathVariable Long EpicId) {
        return  storyRepo.getStoryByEpic(EpicId);
    }

    @PutMapping("/updateStoryStatus/{id}/{status}")
    public void updateStoryStatus(@PathVariable Long id,@PathVariable String status) {
        Story story2 = storyRepo.findById(id).orElseThrow(() -> new UsernotFoundException("Story by id " + id + "was not found"));
        story2.setStoryStatus(status);
        storyRepo.save(story2);
    }
}
