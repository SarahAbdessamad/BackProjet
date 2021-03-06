package com.example.backprojet.repo;

import com.example.backprojet.model.Note;
import com.example.backprojet.model.Project;
import com.example.backprojet.model.Users;
import lombok.var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
/*
public interface UsersRepo extends JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.nom = :nom")
    public Users getUserByname(@Param("nom") String nom);
    //public Users getUserByLetter(@Param("letter") char l);
}
 */
public interface UsersRepo extends JpaRepository<Users, String> , CrudRepository<Users, String>{
    @Query("SELECT u FROM Users u WHERE u.userName = :nom")
    List<Users> getUserByname(@Param("nom") String nom);
//Select project_id from users_enrolled where id ="abdou";

    @Query("SELECT u FROM Users u ORDER BY u.experience DESC")
    public Iterable<Users> findByExperience();

    @Query("SELECT u FROM Users u WHERE u.skill = :skill ")
    public Iterable<Users> findBySkill(@Param("skill") String skill);

    @Query("SELECT u FROM Users u WHERE u.userName LIKE  CONCAT('%',:nom,'%')")
    List<Users> getUserBynames(@Param("nom") String nom);

   // Optional<Users> findByEmailAndPassword(String email, String password);


}