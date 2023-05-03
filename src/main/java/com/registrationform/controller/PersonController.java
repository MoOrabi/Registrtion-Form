package com.registrationform.controller;

import com.registrationform.entity.Person;
import com.registrationform.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonController {

        @Autowired
        private PersonService personService;
        
        
        @PostMapping("/add")
        public ResponseEntity<?> addPerson(@RequestBody Person person){
            return ResponseEntity.ok(personService.addPerson(person));
        }

        @PutMapping("/update")
        public ResponseEntity<?> updatePerson(@RequestBody Person person){
            return ResponseEntity.ok(personService.update(person));
        }

        @GetMapping("/getAll")
        public ResponseEntity<?> getAll(){
            return ResponseEntity.ok(personService.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<?> getPersonById(@PathVariable String id) {
            return ResponseEntity.ok(personService.findById(id));
        }
        @DeleteMapping("/{id}")
        public void deletePerson(@PathVariable String id){
            personService.delete(id);
        }
}
