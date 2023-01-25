package com.api.application.controllers;

import com.api.application.entities.Person;
import com.api.application.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody Person person){
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Person> update(@RequestBody Person person, @PathVariable Long id){
        return new ResponseEntity<>(personService.update(person, id), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id){
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll(){
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }
}
