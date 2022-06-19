package com.example.backprojet.controllor;

import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.Project;
import com.example.backprojet.model.Users;
import com.example.backprojet.repo.ProjectRepo;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.service.EmailSencerService;
import com.example.backprojet.service.UserService;
import com.example.backprojet.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.*;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    private EmailSencerService javaMailSender;

    @Autowired
    private UserService userService;
    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerNewUser/{RoleName}"})
    public Users registerNewUser(@RequestBody Users user,@PathVariable String RoleName) {
        String coordinates="these are your coordinates to access our plateform \n username: "+user.getUserName().toString()
                +"\n userPassword: "+user.getUserPassword();
        javaMailSender.sendEmail(user.getEmail(),
                "coordinates to access TaskForce",
                coordinates);
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

    @RequestMapping("getByUserName/{username}")
    public Users getByUserName(@PathVariable String username){
        List<Users> users  =  userRepo.getUserByname(username);
        Users user1 = users.get(0);
        return user1;
    }

    @RequestMapping("/findsUser/{username}")
    public List<Users> findByNames(@PathVariable String username){
        return (List<Users>) userRepo.getUserBynames(username);
    }

    @PutMapping("/updateUserByID/{username}")
    public ResponseEntity<Users>  addskill(@PathVariable String username,@RequestBody Users user){

        List<Users> users  =  userRepo.getUserByname(username);
        Users user1 = users.get(0);
        user1.setUserFirstName(user.getUserFirstName());
        user1.setUserLastName(user.getUserLastName());
        user1.setUserPassword(getEncodedPassword(user.getUserPassword()));
        user1.setMobile(user.getMobile());
        user1.setPost(user.getPost());
        user1.setDepartement(user.getDepartement());
        user1.setExperience(user.getExperience());
        user1.setEmail(user.getEmail());

        Users updateduser = userRepo.save(user1);
      return   new ResponseEntity<>(updateduser, HttpStatus.OK);
    }




    @PutMapping("/{username}/addskill/{skill}")
    public ResponseEntity<Users> addskill(@PathVariable String username,@PathVariable String skill,
                                               @RequestBody Users user) {
        List<Users> users  =  userRepo.getUserByname(username);
        Users user1 = users.get(0);
        List<String> skills =  user1.getSkill();
        //ArrayList<String> list = new ArrayList<>();
        for(int i = 0 ; i < skills.size(); i++)
            System.out.println(skills.get(i));
        skills.add(skill);
        user1.setSkill(skills);
        Users updateduser =  userRepo.save(user1);
        return new ResponseEntity<>(updateduser, HttpStatus.OK);
    }

    @RequestMapping("/findUserRole/{userRole}")
    public List<Users> findByRole(@PathVariable String userRole){

        List<Users> users = userRepo.findAll();
        List<Users> filtred_users = new ArrayList<>();
        Iterator<Users> i = users.iterator();
        System.out.println("etape 0");
        System.out.println(filtred_users);


        while (i.hasNext()) {
            Users i2 = i.next();
            System.out.println("i2 role = " + i2.getRole().iterator().next().getRoleName());
            if (Objects.equals( i2.getRole().iterator().next().getRoleName(), userRole)) {

                System.out.println("etape 1");
                //System.out.println(i.next());
                System.out.println(i2);
                filtred_users.add(i2);
            }
        }

        return filtred_users;
    }
    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

}
