package com.example.backprojet.controllor;


import com.example.backprojet.model.Dependency;
import com.example.backprojet.repo.DependencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dependency")
public class DependencyController {
    private Dependency dependency;
    @Autowired
    private DependencyRepo dependencyRepo;

    @PostMapping("/addDependency")
    Dependency addDependency(@RequestBody Dependency dependency) {
        return dependencyRepo.save(dependency);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Dependency>> getAllDependencies() {
        List<Dependency> dependencies = dependencyRepo.findAll();
        return new ResponseEntity<List<Dependency>>(dependencies, HttpStatus.OK);
    }
}
