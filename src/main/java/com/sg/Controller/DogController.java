package com.sg.Controller;

import com.sg.Model.Dogs;
import com.sg.Service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/start")
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<List<Dogs>> getDogs(){
        //List<Dogs> listy = dogService.getAllDogs();


        return ResponseEntity.status(HttpStatus.OK).body(dogService.getAllDogs());
    }

    @GetMapping(value = "/getOne/{id}")
    public ResponseEntity<Dogs> getDogById(@PathVariable String id){
        Dogs returnedDog = dogService.getDogById(id);
        return ResponseEntity.status(HttpStatus.OK).body(returnedDog);
    }

    @PostMapping(value = "/addDog")
    public ResponseEntity<Dogs> addAttempts(@RequestBody Dogs dogs){
        dogService.addDogs(dogs);


        return ResponseEntity.status(HttpStatus.OK).body(dogs);
    }

    @PutMapping(value = "update")
    public ResponseEntity<String> updateDogList(@RequestBody Dogs dog){
        dogService.updateDogs(dog);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dog.getId() + " has been updated");

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteDogById(@PathVariable String id){
        dogService.deleteById(id);

        System.out.println("does nothing here");

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Dog with Id number -" + id + "- was deleted");
    }

}
