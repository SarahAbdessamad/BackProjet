package com.example.backprojet.repo;


import com.example.backprojet.model.SubTaskMapping;
import com.example.backprojet.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubTaskMappingRepo extends JpaRepository<SubTaskMapping, Long> {

    List<SubTaskMapping> findAllByParentTask(Task parentTask);
}

