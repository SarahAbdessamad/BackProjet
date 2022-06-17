package com.example.backprojet.repo;

import com.example.backprojet.model.Epic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EpicRepo  extends JpaRepository<Epic, Long>, CrudRepository<Epic, Long> {

    @Query("SELECT e FROM Epic e WHERE e.EpicTitle LIKE CONCAT('%',:EpicTitle,'%')   ")
    public Iterable<Epic> getEpicByEpicTitle(@Param("EpicTitle") String EpicTitle);


    @Query("SELECT e FROM Epic e WHERE e.ProjectId = :ProjectId")
    List<Epic> getEpicByProject(@Param("ProjectId") Long ProjectId);

}
