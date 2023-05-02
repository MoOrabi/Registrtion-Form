package com.registrationform.controller;

import com.registrationform.entity.User;
import com.registrationform.services.PersonService;
import com.registrationform.utils.ArabicUtils;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/person")
public class PersonControl {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<?> saveUser(@Valid @RequestBody User user) {
        // Remove diacritics from first name, last name, and dar name
        user.setFirstName(ArabicUtils.removeDiacritics(user.getFirstName()));
        user.setLastName(ArabicUtils.removeDiacritics(user.getLastName()));
        user.setDarName(ArabicUtils.removeDiacritics(user.getDarName()));

        User savedUser = personService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = personService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
         personService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return personService.getAllUsers();
    }
}
