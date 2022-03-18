package com.example.backprojet.service;

import com.example.backprojet.model.Participants;
import com.example.backprojet.repo.ParticipantsRepo;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ParticipantsService {
    private ParticipantsRepo participantsRepo;
    public Optional<Participants> findParticipantsById(Long id)  {
        return participantsRepo.findById(id);
    }

}
