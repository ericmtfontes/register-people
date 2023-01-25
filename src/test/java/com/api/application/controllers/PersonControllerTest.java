package com.api.application.controllers;

import com.api.application.entities.Person;
import com.api.application.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {

    @InjectMocks
    private PersonController personController;

    @Mock
    private PersonService personService;

    private Person person;
    private List<Person> people;
    private Long id;

    @BeforeEach
    void start(){
        person = new Person();
        people = new ArrayList<>();
        id = 1L;
    }

    @Test
    void create(){
        var response = assertDoesNotThrow(() -> personController.create(person));
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), response);
    }

    @Test
    void update(){
        when(personService.update(person, id)).thenReturn(person);
        var response = assertDoesNotThrow(() -> personController.update(person, id));
        assertNotNull(response);
        assertEquals(new ResponseEntity<>(personService.update(person, id), HttpStatus.OK), response);
    }

    @Test
    void findById(){
        when(personService.findById(id)).thenReturn(person);
        var response = assertDoesNotThrow(() -> personController.findById(id));
        assertNotNull(response);
        assertEquals(new ResponseEntity<>(personService.findById(id), HttpStatus.OK), response);
    }

    @Test
    void findAll(){
        when(personService.findAll()).thenReturn(people);
        var response = assertDoesNotThrow(() -> personController.findAll());
        assertNotNull(response);
        assertEquals(new ResponseEntity<>(personService.findAll(), HttpStatus.OK), response);
    }
}
