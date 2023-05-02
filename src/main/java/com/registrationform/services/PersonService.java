package com.registrationform.services;

import com.registrationform.entity.User;
import com.registrationform.exception.NotFoundException;
import com.registrationform.repository.PersonRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PersonService {

    @Autowired
    private PersonRepo personRepo;

    public User save(User user){
        return personRepo.save(user);
    }

    public User getUserById(Long id) {
        return personRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
    }

    public List<User> getAllUsers() {
        return personRepo.findAll();
    }

    public List<User> findAll(){
        return personRepo.findAll();
    }

    public void deleteUser(Long id) {
        User user = personRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id " + id));
        personRepo.delete(user);
    }








}
