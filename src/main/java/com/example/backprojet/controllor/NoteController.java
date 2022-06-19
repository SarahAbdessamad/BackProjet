package com.example.backprojet.controllor;

import com.example.backprojet.exception.UsernotFoundException;

import com.example.backprojet.model.Note;
import com.example.backprojet.model.Project;
import com.example.backprojet.repo.NoteRepo;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/note")
public class NoteController {

private Note note;

@Autowired
private NoteRepo noteRepo;
@Autowired
private NoteService noteService;
@Autowired
private  ProjectRepo projectRepo;
@Autowired
private  UsersRepo usersRepo;

public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/addnote")
    public void addNote(@RequestBody Note note) {
        //Project noteProject  = projectRepo.getById(note.getProjectId());
        System.out.println("note");
        //note.setProject(noteProject);
        Project noteProject  = projectRepo.getById(note.getProjectId());
        note.setProject(noteProject);
        noteRepo.save(note);
    }
    @RequestMapping("/find/{id}")
    public Optional<Note> getNoteById(@PathVariable(value = "id") Long IdNote) {
        return noteRepo.findById(IdNote);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNote() {
        List<Note> note = noteRepo.findAll();
        return new ResponseEntity<>(note, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{IdNote}")
    public ResponseEntity<?> deleteNote(@PathVariable("IdNote") Long IdNote) {
        noteService.deleteNote(IdNote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/updateByID/{id}")
    public ResponseEntity<Note> updateNotebyId(@PathVariable Long id, @RequestBody Note note) {
        Note note1 = noteRepo.findById(id).orElseThrow(() -> new UsernotFoundException("Note by id " + id + "was not found"));
        note1.setNoteDate(note.getNoteDate());
        note1.setNoteBody(note.getNoteBody());
        Note updatedNote = noteRepo.save(note1);
        return new ResponseEntity<>(updatedNote, HttpStatus.OK);
    }

    @RequestMapping("/findNote/{NoteAuthor}")
    public List<Note> getNoteByNoteRole(@PathVariable String NoteAuthor) {
        return (List<Note>) noteRepo.getNoteByNoteAuthor(NoteAuthor);
    }
/*
    @GetMapping("/findusers/{name}")
    public Iterable<Note> findByUsers(@PathVariable String name){
    return (Iterable<Note>) noteRepo.findByUsers(usersRepo.findById(name));
    }

 */
@GetMapping("/{projectId}/find")
List<Note> GetNotes(
        @PathVariable Long projectId
) {
    Project project = projectRepo.findById(projectId).get();
    System.out.println(project);
    List<Note> notes = project.getNotes();
    System.out.println(notes);
    return notes;
}
    @DeleteMapping("/{idnote}/delete/{username}")
    ResponseEntity<String> deletenote(
            @PathVariable String username,
            @PathVariable Long idnote
    ) {
        Note note = noteRepo.findById(idnote).get();

        if (Objects.equals(username, note.getNoteAuthor())){
            noteService.deleteNote(idnote);
        }
        return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body("You are not the author of this note ");
    }

}

