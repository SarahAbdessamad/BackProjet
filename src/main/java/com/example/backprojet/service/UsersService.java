package com.example.backprojet.service;


import com.example.backprojet.model.Users;
import com.example.backprojet.repo.UsersRepo;
import lombok.var;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UsersService {
    private UsersRepo usersRepo;
    private ArrayList<Object> users;

    @Autowired
    public UsersService(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
    }
    /*
           public List<Users> findByName() {
            return usersRepo.findAll();
        }
     */
}
