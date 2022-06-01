package com.example.backprojet.repo;


import com.example.backprojet.model.Epic;
import com.example.backprojet.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoryRepo extends JpaRepository<Story, Long>, CrudRepository<Story, Long>  {
    @Query("SELECT s FROM Story s WHERE s.StoryTitle LIKE CONCAT('%',:StoryTitle,'%')   ")
    public Iterable<Story> getStoryByStoryTitle(@Param("StoryTitle") String StoryTitle);

    /*@Query("SELECT s FROM Story s WHERE s.EpicId = :EpicId")
    List<Story> getStoryByEpic(@Param("EpicId") String EpicId);*/
}
