package com.example.backprojet.controllor;


import com.example.backprojet.model.Users;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.UserService;
import com.example.backprojet.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {



    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser/{RoleName}"})
    public Users registerNewUser(@RequestBody Users user,@PathVariable String RoleName) {
        return userService.registerNewUser(user,RoleName);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('User')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }

    // li fet tebe3 securitty jwt project

    @Autowired
    UsersRepo userRepo;
    private UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }



    /*
        @GetMapping("/all")
        public ResponseEntity<Users> all(@RequestBody Users users) {
            usersRepo.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        }
     */
    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUSERS() {
        List<Users> users = (List<Users>) userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
/*
    @GetMapping("/find")
    public ResponseEntity<Users> getUserByname(@RequestParam String nom) {
        Users users = usersRepo.getUserByname(nom);
        if(users == null){
            //handler your own exception here
        }
        //show the student as json object
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }
    */

    /*@GetMapping("/findByLetter")
    public ResponseEnstity<Users> getUserByLetter(@RequestParam char l){
        Users users = usersRepo.getUserByname(nom);

    }*/

    @RequestMapping("/find/{nom}")
    public List<Users> getUserByname(@PathVariable String nom) {
        return (List<Users>) userRepo.getUserByname(nom);
    }
    @RequestMapping("/findaaaaaaaaa/{nom}")
    public List<Users> getUserBynamdde(@PathVariable String nom) {
        return (List<Users>) userRepo.getUserByname(nom);
    }

/*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        //UsersRepo.deleteUser(id);
        //return new ResponseEntity<>(HttpStatus.OK);
        return (List<Users>) usersRepo.deleteUser(id);
    }

 */
    @GetMapping("/findUserExp")
    public List<Users> findByExperience(){
        return (List<Users>) userRepo.findByExperience();

}
    @RequestMapping("/findUser/{skill}")
    public List<Users> findBySpeciality(@PathVariable String skill){
        return (List<Users>) userRepo.findBySkill(skill);
    }


}
