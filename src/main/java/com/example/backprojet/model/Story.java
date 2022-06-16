package com.example.backprojet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    //@Transient
    private long EpicId;

    private String Priority;

    @JsonIgnore
    @ManyToOne
    private Epic epic;

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }

     @OneToMany(mappedBy = "story" , cascade = CascadeType.REMOVE)
    private List<Task> tasks;

    public List<String> getRequiredSkills() {
        List<String> skills = new ArrayList<>();
        this.tasks.forEach(task -> skills.add(task.getRequiredSkill()));
        return skills;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Story() {
    }

    public Story(Long storyId, String storyTitle, String storyDescription, String storyStartdate, String storyDeadline, String storyStatus, long epicId, String priority) {
        StoryId = storyId;
        StoryTitle = storyTitle;
        StoryDescription = storyDescription;
        StoryStartdate = storyStartdate;
        StoryDeadline = storyDeadline;
        StoryStatus = storyStatus;
        EpicId = epicId;
        Priority = priority;
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

    public long getEpicId() {
        return EpicId;
    }

    public void setEpicId(long epicId) {
        EpicId = epicId;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
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
                '}';
    }
}
