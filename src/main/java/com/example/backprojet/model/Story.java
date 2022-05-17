package com.example.backprojet.model;


import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "StoryId", nullable = false)
    private Long StoryId;

    @Column(length = 2048)
    private String StoryTitle;
    @Column(length = 2048)
    private String StoryDescription;
    private String StoryStartdate;
    private String StoryDeadline;
    private String StoryStatus;
    private String EpicId;
    private String Priority;

    //@ModelAttribute("requiredSkills")
    //ArrayList<String> requiredSkills;

    @ElementCollection
    private List<String> requiredSkills;

    public Story() {
    }

    public Story(Long storyId, String storyTitle, String storyDescription, String storyStartdate, String storyDeadline, String storyStatus, String epicId, String priority, List<String> requiredSkills) {
        StoryId = storyId;
        StoryTitle = storyTitle;
        StoryDescription = storyDescription;
        StoryStartdate = storyStartdate;
        StoryDeadline = storyDeadline;
        StoryStatus = storyStatus;
        EpicId = epicId;
        Priority = priority;
        this.requiredSkills = requiredSkills;
    }

    public Long getStoryId() {
        return StoryId;
    }

    public void setStoryId(Long storyId) {
        StoryId = storyId;
    }

    public String getStoryTitle() {
        return StoryTitle;
    }

    public void setStoryTitle(String storyTitle) {
        StoryTitle = storyTitle;
    }

    public String getStoryDescription() {
        return StoryDescription;
    }

    public void setStoryDescription(String storyDescription) {
        StoryDescription = storyDescription;
    }

    public String getStoryStartdate() {
        return StoryStartdate;
    }

    public void setStoryStartdate(String storyStartdate) {
        StoryStartdate = storyStartdate;
    }

    public String getStoryDeadline() {
        return StoryDeadline;
    }

    public void setStoryDeadline(String storyDeadline) {
        StoryDeadline = storyDeadline;
    }

    public String getStoryStatus() {
        return StoryStatus;
    }

    public void setStoryStatus(String storyStatus) {
        StoryStatus = storyStatus;
    }

    public String getEpicId() {
        return EpicId;
    }

    public void setEpicId(String epicId) {
        EpicId = epicId;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    @Override
    public String toString() {
        return "Story{" +
                "StoryId=" + StoryId +
                ", StoryTitle='" + StoryTitle + '\'' +
                ", StoryDescription='" + StoryDescription + '\'' +
                ", StoryStartdate='" + StoryStartdate + '\'' +
                ", StoryDeadline='" + StoryDeadline + '\'' +
                ", StoryStatus='" + StoryStatus + '\'' +
                ", EpicId='" + EpicId + '\'' +
                ", Priority='" + Priority + '\'' +
                ", requiredSkills=" + requiredSkills +
                '}';
    }
}
