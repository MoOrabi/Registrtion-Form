package com.registrationform.controller;

import com.registrationform.entity.Person;
import com.registrationform.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonControl {

        @Autowired
        private PersonService personService;
        @PostMapping
        public ResponseEntity<?> addPerson(@RequestBody Person person){
            return ResponseEntity.ok(personService.addPerson(person));
        }

        @PutMapping
        public ResponseEntity<?> updatePerson(@RequestBody Person person){
            return ResponseEntity.ok(personService.update(person));
        }

        @GetMapping
        public ResponseEntity<?> getAll(){
            return ResponseEntity.ok(personService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getPersonById(@PathVariable Long id) {
            return ResponseEntity.ok(personService.findById(id));
        }
        @DeleteMapping
        public void deletePerson(Long id){
            personService.delete(id);
        }
}
