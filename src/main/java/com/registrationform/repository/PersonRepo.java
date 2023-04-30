package com.registrationform.repository;

import com.registrationform.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long> {


    Optional<Person> findByZipcode(String zip);

}