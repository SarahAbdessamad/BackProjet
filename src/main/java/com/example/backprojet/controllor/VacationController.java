package com.example.backprojet.controllor;


import com.example.backprojet.exception.UsernotFoundException;
import com.example.backprojet.model.*;
import com.example.backprojet.repo.UsersRepo;
import com.example.backprojet.repo.VacationRepo;
import com.example.backprojet.service.StoryService;
import com.example.backprojet.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vacation")
public class VacationController {
        private Vacation vacation;

        @Autowired
        private VacationRepo vacationRepo;
        @Autowired
        private UsersRepo usersRepo;

        @Autowired
        private VacationService vacationService;

        public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

        @PostMapping("/addVacation")
        void addVacation(@RequestBody Vacation vacation) {
            System.out.println("etpae 0");
            System.out.println(vacation.userName+"lekher username");
            List<Users> users  =  usersRepo.getUserByname(vacation.getUserName());
            Users user = users.get(0);

            System.out.println(user.toString());
       //    Users vacationUsers  = usersRepo.getById(vacation.getUserName());

         //  System.out.println("hedha el user passe "+ vacationUsers.toString());
          vacation.setUsers(user);
                vacationRepo.save(vacation);
        }

        @RequestMapping("/find/{id}")
        public Optional<Vacation> getNoteById(@PathVariable(value = "id") Long IdVacation) {
            return vacationRepo.findById(IdVacation);
        }

        @GetMapping("/all")
        public ResponseEntity<List<Vacation>>  getAllVacation() {
            List<Vacation> Vacations = vacationRepo.findAll();
            return new ResponseEntity<>(Vacations, HttpStatus.OK);
        }


        @DeleteMapping("/delete/{NoteId}")
        public ResponseEntity<?> deleteVacation(@PathVariable("NoteId") Long IdVacation) {
            vacationService.deleteVacation(IdVacation);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        @PutMapping("/updateByID/{id}")
        public ResponseEntity<Vacation> updateVacationbyId(@PathVariable Long id, @RequestBody Vacation vacation) {
            Vacation vacation1 = vacationRepo.findById(id).orElseThrow(() -> new UsernotFoundException("Vacation by id " + id + "was not found"));
            vacation1.setVacationStartDate(vacation.getVacationStartDate());
            vacation1.setVacationEndDate(vacation.getVacationEndDate());

            Vacation updatedVacation = vacationRepo.save(vacation1);
            return new ResponseEntity<>(updatedVacation, HttpStatus.OK);
        }

    @RequestMapping("/findVacationsByUserName/{username}")
    public List<Vacation> getvacationByUserName(@PathVariable String username) {
        return (List<Vacation>) vacationRepo.getvacationByUserName(username);
    }





}
