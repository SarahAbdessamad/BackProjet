package com.example.backprojet.controllor;

import com.example.backprojet.model.Participants;
import com.example.backprojet.repo.ParticipantsRepo;
import com.example.backprojet.service.ParticipantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return new ResponseEntity<List<Participants>>(participants, HttpStatus.OK);
    }
    @RequestMapping("/find/{id}")
    public Optional<Participants> getParticipantsById (@PathVariable(value = "id") Long participantsId) {
        return participantsRepo.findById(participantsId);
    }
    /*

    @RequestMapping(value="/find/{id}")
    public Participants findParticipantsById (@PathVariable Long participantsId) {
        return participantsService.findParticipantsById(participantsId);
        Optional<Participants> participants = participantsService.findParticipantsById(participantsId);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

 */

}
