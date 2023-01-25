package com.api.application.controllers;

import com.api.application.entities.Address;
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
public class AddressControllerTest {

    @InjectMocks
    private AddressController addressController;

    @Mock
    private PersonService personService;

    private Address address;
    private List<Address> addresses;
    private Long id;

    @BeforeEach
    void start(){
        address = new Address();
        addresses = new ArrayList<>();
        id = 1L;
    }

    @Test
    void createAddress(){
        var response = assertDoesNotThrow(() -> addressController.createAddress(address, id));
        assertEquals(new ResponseEntity<>(HttpStatus.CREATED), response);
    }

    @Test
    void findAllAddressById(){
        when(personService.findAllAddressById(id)).thenReturn(addresses);
        var response = assertDoesNotThrow(() -> addressController.findAllAddressById(id));
        assertNotNull(response);
        assertEquals(new ResponseEntity<>(personService.findAllAddressById(id), HttpStatus.OK), response);
    }
}
