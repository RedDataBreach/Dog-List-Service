package com.sg.Service;

import com.sg.Exception.EmptyDogListException;
import com.sg.Exception.EmptyInputException;
import com.sg.Model.Dogs;
import com.sg.Repository.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class DogService{

    @Autowired
    private repository repo;

    public List<Dogs> getAllDogs(){
        List<Dogs> dogs = repo.findAll();

        if(dogs.isEmpty()){
            throw new EmptyDogListException();
        }

        return dogs;
    }

    public void addDogs(Dogs dogs){

        if(dogs.getName().isEmpty() || dogs.getName().length() == 0){
            throw new EmptyInputException("601", "Input Fields are empty");
        }



        repo.insert(dogs);
    }

    public void updateDogs(Dogs dog){
        Dogs savedDog = repo.findById(dog.getId())
                .orElseThrow(() -> new RuntimeException(
                        String.format("Cannot Find Dog by Id %s", dog.getId())));

        savedDog.setId(dog.getId());
        savedDog.setName(dog.getName());
        savedDog.setAge(dog.getAge());

        repo.save(savedDog);

    }

    // I can throw an error here

    // If there is no matching dogId, then the program will throw NoSuchElementException
    public Dogs getDogById(String dogId) {

        /** findById(ID parameter) returns an Optional of that type of object we are working with.
         *
         *  Since our objects are dogs, we are working on Generic Dogs type
         *
         */

        Optional<Dogs> dogValue = repo.findById(dogId);


        if(dogValue.isPresent()){
            return dogValue.get();
        }else{
            throw new NoSuchElementException();
        }


    }

    public void deleteById(String id) {
        repo.deleteById(id);
    }
}
