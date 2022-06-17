package com.example.backprojet.service;


import com.example.backprojet.repo.EpicRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class EpicService {
    private EpicRepo epicRepo;
    @Autowired
    public EpicService(EpicRepo epicRepo) {
        this.epicRepo = epicRepo;
    }

    public void deleteEpic(Long EpicId){
        epicRepo.deleteById(EpicId);
    }

}
