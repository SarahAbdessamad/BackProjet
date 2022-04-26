package com.example.backprojet.repo;

import com.example.backprojet.model.Project;
import com.example.backprojet.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>, CrudRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.ProjectTitle LIKE CONCAT('%',:ProjectTitle,'%')   ")
    public Iterable<Project> getProjectByProjectTitle(@Param("ProjectTitle") String ProjectTitle);


}
