package com.example.backprojet.service;

import com.example.backprojet.model.Participants;
import com.example.backprojet.repo.ParticipantsRepo;
import com.example.backprojet.repo.ProjectRepo;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@Transactional
public class ParticipantsService {
    private ParticipantsRepo participantsRepo;
    public Participants findParticipantsById(Long id) throws UserPrincipalNotFoundException {
        return participantsRepo.findById(id)
                .orElseThrow(() -> new UserPrincipalNotFoundException("User by id " + id + " was not found"));
    }

}
