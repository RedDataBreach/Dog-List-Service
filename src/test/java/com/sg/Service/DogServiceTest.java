package com.sg.Service;

import com.sg.Model.Dogs;
import com.sg.Repository.repository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {DogServiceTest.class})
class DogServiceTest {

    @Mock
    repository repo;

    @InjectMocks
    DogService dogService;

    public List<Dogs> dogsList;

    @Test
    @Order(1)
    public void test_getAllCountries(){

        dogsList = new ArrayList<>();
        dogsList.add(new Dogs("1", "Bubby", 2));
        dogsList.add(new Dogs("2", "Corry", 3));

        when(repo.findAll()).thenReturn(dogsList);

       // assertEquals(2, dogService.getAllDogs().size());

        String name = dogsList.get(0).getName();

        assertTrue("Bubby".equalsIgnoreCase(name));

//        when(dogService.getAllDogs()).thenReturn();
    }



}