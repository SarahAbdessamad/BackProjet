package com.example.backprojet.repo;

import com.example.backprojet.model.Note;
import com.example.backprojet.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepo extends JpaRepository<Note, Long>, CrudRepository<Note, Long> {

    @Query("SELECT n FROM Note n WHERE n.NoteAuthor = :NoteAuthor")
    List<Note> getNoteByNoteAuthor(@Param("NoteAuthor") String NoteAuthor);

    //public Iterable<Note> findByUsers(Optional<Users> User);

}
