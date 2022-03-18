package com.example.backprojet.service;


import com.example.backprojet.model.Users;
import com.example.backprojet.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsersService {
    @Autowired
    private UsersRepo usersRepo;
/*
    @Autowired
    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }

       public List<Users> findByName() {
        return usersRepo.findAll();
    }

 */
}
