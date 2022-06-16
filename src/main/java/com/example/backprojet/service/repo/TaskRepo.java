package com.example.backprojet.service.repo;


import com.example.backprojet.model.Task;
import com.example.backprojet.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>, CrudRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.StoryId = :StoryId")
    List<Task> getTaskByStory(@Param("StoryId") String StoryId);

    @Query("SELECT t FROM Task t WHERE t.requiredSkill = :requiredSkill and t.TaskId= :TaskId")
    public Iterable<Task> findByRequiredSkill(@Param("requiredSkill") String requiredSkill);

    @Override
    public  Task save(Task task) ;

}

