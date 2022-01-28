package com.sg.Controller;

import com.sg.Exception.EmptyDogListException;
import com.sg.Model.Dogs;
import com.sg.Service.DogService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder.*;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@ExtendWith(MockitoExtension.class)
@WebMvcTest(DogController.class)
class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DogService dogServiceMock;

    @InjectMocks
    DogController dogController;

    List<Dogs> dogsList;

    @BeforeEach
    public void setUp(){
        //mockMvc = MockMvcBuilders.standaloneSetup(dogController).build();
        dogsList = new ArrayList<>();
        dogsList.add(new Dogs("1", "Josh", 2));
        dogsList.add(new Dogs("2", "Mater", 4));
    }

    @Test
    void shouldCreateMock(){
        assertNotNull(mockMvc);

    }

    @Test
    public void findAll_ListIsPopulated_ShouldReturnHttpStatusCode200() throws Exception{
        when(dogServiceMock.getAllDogs()).thenReturn(dogsList);

        mockMvc.perform(
                get("/start/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", Matchers.is(2)))
                .andExpect(jsonPath("$[0].name").value("Josh"));
                //.andExpect(content().string("worth"));

        verify(dogServiceMock, times(1)).getAllDogs();
        verifyNoMoreInteractions(dogServiceMock);

        System.out.println(dogsList.get(1).getName());
    }

    @Test
    public void findAll_ListIsEmpty_ShouldReturnHttpStatusCode404() throws Exception{
        dogsList = new ArrayList<>();
        System.out.println(dogsList.size());

        when(dogServiceMock.getAllDogs()).thenThrow(EmptyDogListException.class);

        mockMvc.perform(
                        get("/start/getAll"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("The List is empty, add a datapoint to it"));

    }


    @Test
    public void findById_DogIdNotFound_ShouldReturnHttpStatusCode404() throws Exception{
        when(dogServiceMock.getDogById("2")).thenThrow(new NoSuchElementException());

        mockMvc.perform(
                get("/start/getOne/{id}", "2"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("There is no Dog matching that Id"));
    }

    @Test
    public void Input_Valid_Add_Dog_To_Database(){
        Dogs Shep = new Dogs("1", "Jameson", 12);


    }





//    @Test
//    public void testData(){
//        dogsList.add(new Dogs("1", "jso", 2));
//
//        when(dogServiceMock.getAllDogs())
//                .thenReturn(dogsList);
//
////        String name = dogController.getDogs().get(0).getName();
////
////        assertEquals(1, dogController.getDogs().size());
//    }
//
//    @Test
//    public void testGetMethod() throws Exception{
//        mockMvc.perform(get("/getAll"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("word"));
//    }

}