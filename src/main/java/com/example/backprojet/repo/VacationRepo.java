package com.example.backprojet.repo;

import com.example.backprojet.model.Project;
import com.example.backprojet.model.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepo extends JpaRepository<Vacation, Long>, CrudRepository<Vacation, Long> {

    @Query("SELECT p FROM Vacation p WHERE p.userName =:userName   ")
    public Iterable<Vacation> getvacationByUserName(@Param("userName") String userName);
}
