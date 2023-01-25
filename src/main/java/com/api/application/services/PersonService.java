package com.api.application.services;

import com.api.application.entities.Address;
import com.api.application.entities.Person;
import com.api.application.handler.NotFoundException;
import com.api.application.repositories.AddressRepository;
import com.api.application.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    public void create(Person person){
        personRepository.save(person);
        for(Address address: person.getAddresses()) {
            address.setPerson(person);
            addressRepository.save(address);
        }
    }

    public Person update(Person person, Long id){
        Person entity = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Unregistered person"));
        entity.setName(person.getName());
        entity.setBirthDate(person.getBirthDate());
        entity.getAddresses().clear();
        for(Address address: person.getAddresses()) {
            address.setPerson(entity);
            entity.getAddresses().add(address);
        }
        return personRepository.save(entity);
    }

    public Person findById(Long id){
        return personRepository.findById(id).orElseThrow(() -> new NotFoundException("Unregistered person"));
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public void createAddress(Address address, Long id){
        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundException("Unregistered person"));
        address.setPerson(person);
        addressRepository.save(address);
    }

    public List<Address> findAllAddressById(Long id) {
        List<Address> entities = addressRepository.findAll();
        List<Address> result = new ArrayList<>();
        for(Address address : entities){
            if(address.getPerson().getId() == id){
                result.add(address);
            }
        }
        return result;
    }
}
