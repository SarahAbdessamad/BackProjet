package com.example.backprojet.repo;


import com.example.backprojet.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepo extends JpaRepository<Task, Long>, CrudRepository<Task, Long> {
    @Query("SELECT u FROM Task u WHERE u.projectId = :projectId")
    List<Task> getTaskByProject(@Param("projectId") String projectId);


}
