package com.example.backprojet.controllor;


import com.example.backprojet.model.Users;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.UsersService;
import lombok.var;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    UsersRepo usersRepo;
    private UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/addusers")
    void addUser(@RequestBody Users users) {
        usersRepo.save(users);
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
        List<Users> users = usersRepo.findAll();
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
        return (List<Users>) usersRepo.getUserByname(nom);
    }
    @RequestMapping("/findaaaaaaaaa/{nom}")
    public List<Users> getUserBynamdde(@PathVariable String nom) {
        return (List<Users>) usersRepo.getUserByname(nom);
    }

/*
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        //UsersRepo.deleteUser(id);
        //return new ResponseEntity<>(HttpStatus.OK);
        return (List<Users>) usersRepo.deleteUser(id);
    }

 */

}
