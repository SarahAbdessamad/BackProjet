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

    @Query("SELECT p FROM Project p WHERE p.ProjectLocation LIKE CONCAT('%',:ProjectLocation,'%')   ")
    public Iterable<Project> getProjectByProjectLocation(@Param("ProjectLocation") String ProjectLocation);

    @Query("SELECT p FROM Project p WHERE p.ProjectStatus LIKE CONCAT('%',:ProjectStatus,'%')   ")
    public Iterable<Project> getProjectByProjectStatus(@Param("ProjectStatus") String ProjectStatus);

    @Query("SELECT p FROM Project p WHERE p.Client LIKE CONCAT('%',:Client,'%')   ")
    public Iterable<Project> getProjectByClient(@Param("Client") String Client);

    @Query("SELECT p FROM Project p WHERE p.ProjectStatus <> 'archived' " )
    public Iterable<Project> getUnarchivedProject();

}
