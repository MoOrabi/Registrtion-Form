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

    public Person addPerson(Person entity){
        Optional<Person> person= personRepo.findByZipcode(entity.getZipcode());
        if (person.isPresent())
                throw new BadRequest("Duplicate Zipcode");
        if (entity.getImag()==null)
            throw new BadRequest("Enter url image");

        return personRepo.save(entity);
    }

    public Person update(Person entity){
        return personRepo.save(entity);
    }

    public Optional<Person> findById(Long id){
        Optional<Person> person =personRepo.findById(id);
        if (person.isEmpty()&& !person.isPresent())
            throw new NotFoundException("Not found Person , Enter right id");
        return person;
    }
    public List<Person> findAll(){
        return personRepo.findAll();
    }

    public void delete(Long id){
        Optional<Person> person =personRepo.findById(id);
        if (person.isEmpty()&& !person.isPresent())
            throw new NotFoundException("Not found , or already deleted");
        personRepo.delete(person.get());
    }








}
