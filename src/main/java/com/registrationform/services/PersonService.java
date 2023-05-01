package com.registrationform.services;

import com.registrationform.entity.Person;
import com.registrationform.exception.BadRequest;
import com.registrationform.exception.NotFoundException;
import com.registrationform.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public Person addPerson(Person person){
        if (person.getId().length()>14||person.getId().length()<14)
                throw new BadRequest("Wrong Id");
        if (person.getFirstName()==null||person.getLastName()==null||
        		person.getLevel()==0||person.getImage()==null||
        		person.getTheNameOfDar()==null)
            throw new BadRequest("Enter All required data");
        
        return personRepo.save(person);
    }

    public Person update(Person person){
        return personRepo.save(person);
    }

    public Optional<Person> findById(String id){
        Optional<Person> person =personRepo.findById(id);
        if (person.isEmpty()&& !person.isPresent())
            throw new NotFoundException("Not found Person , Enter right id");
        return person;
    }
    public List<Person> findAll(){
        return personRepo.findAll();
    }

    public void delete(String id){
        Optional<Person> person =personRepo.findById(id);
        if (person.isEmpty()&& !person.isPresent())
            throw new NotFoundException("Not found , or already deleted");
        personRepo.delete(person.get());
    }








}
