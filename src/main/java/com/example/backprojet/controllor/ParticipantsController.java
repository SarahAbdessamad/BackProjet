package com.example.backprojet.controllor;

import com.example.backprojet.model.Participants;
import com.example.backprojet.model.Users;
import com.example.backprojet.repo.ParticipantsRepo;
import com.example.backprojet.service.ParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/Participants")
public class ParticipantsController {
    @Autowired
    ParticipantsRepo participantsRepo;
    private ParticipantsService participantsService;

    public ParticipantsController(ParticipantsService participantsService ) {
        this.participantsService = participantsService;
    }
    @PostMapping("/addParticipants")
    void addParticipants(@RequestBody Participants participants){
    participantsRepo.save(participants);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Participants>> getAllParticipants() {
        List<Participants> participants = participantsRepo.findAll();
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Participants> getEmployeeById (@PathVariable("participantsId") Long participantsId) throws UserPrincipalNotFoundException {
        Participants participants = participantsService.findParticipantsById(participantsId);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }
}
