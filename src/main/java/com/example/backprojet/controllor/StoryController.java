package com.example.backprojet.controllor;


import com.example.backprojet.dto.TaskCreationForm;
import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.Story;
import com.example.backprojet.model.Task;
import com.example.backprojet.repo.StoryRepo;
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
    private StoryService storyService;
    private TaskService taskService;

    public StoryController(StoryService storyService) {
        this.storyService = storyService;
    }


    @PostMapping("/addStory")
    void addStory(@RequestBody Story story) {
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
        story1.setRequiredSkills(story.getRequiredSkills());
        Story updatedStory = storyRepo.save(story1);
        return new ResponseEntity<>(updatedStory, HttpStatus.OK);
    }

    @RequestMapping("/findTitle/{StoryTitle}")
    public List<Story> getStoryByStoryTitle(@PathVariable String StoryTitle) {
        return (List<Story>) storyRepo.getStoryByStoryTitle(StoryTitle);
    }

    @RequestMapping("/findstory/{EpicId}")
    public List<Story> getStoryByEpic(@PathVariable String EpicId) {
        return  storyRepo.getStoryByEpic(EpicId);
    }


}
