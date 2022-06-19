package com.example.backprojet.service;


import com.example.backprojet.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class NoteService {
    private NoteRepo noteRepo;
    @Autowired
    public NoteService(NoteRepo noteRepo) {
        this.noteRepo = noteRepo;
    }

    public void deleteNote(Long IdNote){
        noteRepo.deleteById(IdNote);
    }
}
