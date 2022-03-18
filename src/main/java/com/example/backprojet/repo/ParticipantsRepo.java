package com.example.backprojet.repo;

import com.example.backprojet.model.Participants;
import com.example.backprojet.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface ParticipantsRepo extends JpaRepository<Participants, Long> {
    /*
        @Query("SELECT p FROM Participants p WHERE p.idProject = :idProject and p.idUser =: idUser")
        List<Users> getUserByname(@Param("idProject""idUser") String nom);
    }

     */
}

