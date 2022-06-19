package com.example.backprojet.service;


import com.example.backprojet.repo.NoteRepo;
import com.example.backprojet.repo.VacationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VacationService {
    private VacationRepo vacationRepo;
    @Autowired
    public VacationService(VacationRepo vacationRepo) {
        this.vacationRepo = vacationRepo;
    }
    public void deleteVacation(Long IDVacation){
        vacationRepo.deleteById(IDVacation);
    }
}
