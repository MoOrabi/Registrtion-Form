package com.registrationform.service;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.registrationform.entity.Person;
import com.registrationform.entity.User;
import com.registrationform.exception.BadRequest;
import com.registrationform.repository.PersonRepository;
import com.registrationform.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;
	@Autowired
	private PersonService personServiceUnderTest;
	
	@BeforeEach
	void setUp() {
		personServiceUnderTest = new PersonService(personRepository);
	}

	@Test
	void checkIfPersonIsAdded() {
		Person person = new Person("12345678912345","Mohammed","Orabi","c:/img.jpg",3,"Alarqam");
		personServiceUnderTest.addPerson(person);
		ArgumentCaptor<Person> personArgumentCaptor = 
				ArgumentCaptor.forClass(Person.class);
		verify(personRepository).save(personArgumentCaptor.capture());
		Person capturedPerson = personArgumentCaptor.getValue();
		assertThat(capturedPerson).isEqualTo(person);
		
	}
	
	@Test
	void willThrowWhenIdIsDuplicated() {
		Person person = new Person("12345678912345","Mohammed","Orabi","c:/img.jpg",3,"Alarqam");
		given(personRepository.existsById(person.getId()))
			.willReturn(true);
		assertThatThrownBy(() -> personServiceUnderTest.addPerson(person))
			.isInstanceOf(BadRequest.class)
			.hasMessage("Duplicate Applicant. This Applicant is already added");
		verify(personRepository, never()).save(any());
	}
	
	@Test
	
	void canGetAllStudents() {
		personServiceUnderTest.findAll();
		
		verify(personRepository).findAll();
	}
	
	 public User addPerson(User person){
	        checkPersonIsValid(person);
	        
	        if(personRepository.existsById(person.getId())) {
	        	throw new BadRequest("Duplicate Applicant. This Applicant is already added");
	        }
	        return personRepository.save(person);
	    }

		private void checkPersonIsValid(User person) {
			if (person.getId().length()>14||person.getId().length()<14)
	                throw new BadRequest("Wrong Id");
	        if (person.getFirstName()==null||person.getLastName()==null||
	        		person.getLevel()==0||person.getImage()==null||
	        		person.getTheNameOfDar()==null)
	            throw new BadRequest("Enter All required data");
		}
}
