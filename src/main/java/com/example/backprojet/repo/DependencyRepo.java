package com.example.backprojet.repo;


import com.example.backprojet.model.Dependency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DependencyRepo extends JpaRepository<Dependency, Long>, CrudRepository<Dependency, Long> {
}
