package com.example.backprojet.service;


import com.example.backprojet.service.repo.StoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StoryService {

    private StoryRepo storyRepo;

    @Autowired
    public StoryService(StoryRepo storyRepo) {
        this.storyRepo = storyRepo;
    }

    public void deleteStory(Long StoryId) {
        storyRepo.deleteById(StoryId);
    }

}
