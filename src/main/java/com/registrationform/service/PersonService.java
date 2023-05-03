package com.registrationform.service;

import com.registrationform.entity.User;
import com.registrationform.exception.BadRequest;
import com.registrationform.exception.NotFoundException;
import com.registrationform.repository.PersonRepository;
import com.registrationform.utils.PersonUtils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public User addPerson(User person){
        PersonUtils.checkPersonIsValid(person);
        
        if(personRepository.existsById(person.getId())) {
        	throw new BadRequest("Duplicate Applicant. This Applicant is already added");
        }
        return personRepository.save(person);
    }

    public User getUserById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
    }

    public List<User> getAllUsers() {
        return personRepository.findAll();
    }

    public List<User> findAll(){
        return personRepository.findAll();
    }

    public void deleteUser(Long id) {
        User user = personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        personRepository.delete(user);
    }








}