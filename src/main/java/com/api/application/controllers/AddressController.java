package com.api.application.controllers;

import com.api.application.entities.Address;
import com.api.application.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address/v1")
public class AddressController {

    @Autowired
    PersonService personService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> createAddress(@RequestBody Address address, @PathVariable Long id){
        personService.createAddress(address, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<List<Address>> findAllAddressById(@PathVariable Long id){
        return new ResponseEntity<>(personService.findAllAddressById(id), HttpStatus.OK);
    }
}
