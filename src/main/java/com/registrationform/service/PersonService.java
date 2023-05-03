package com.registrationform.service;

import com.registrationform.entity.Person;
import com.registrationform.exception.BadRequest;
import com.registrationform.exception.NotFoundException;
import com.registrationform.repository.PersonRepository;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person addPerson(Person person){
        checkPersonIsValid(person);
        
        if(personRepository.existsById(person.getId())) {
        	throw new BadRequest("Duplicate Applicant. This Applicant is already added");
        }
        return personRepository.save(person);
    }

	private void checkPersonIsValid(Person person) {
		if (person.getId().length()>14||person.getId().length()<14)
                throw new BadRequest("Wrong Id");
        if (person.getFirstName()==null||person.getLastName()==null||
        		person.getLevel()==0||person.getImage()==null||
        		person.getTheNameOfDar()==null)
            throw new BadRequest("Enter All required data");
	}

    public Person update(Person person){
        return personRepository.save(person);
    }

    public Optional<Person> findById(String id){
        Optional<Person> person =personRepository.findById(id);
        if (person.isEmpty()&& !person.isPresent())
            throw new NotFoundException("Not found Person , Enter right id");
        return person;
    }
    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public void delete(String id){
        Optional<Person> person =personRepository.findById(id);
        if (person.isEmpty()&& !person.isPresent())
            throw new NotFoundException("Not found , or already deleted");
        personRepository.delete(person.get());
    }








}
