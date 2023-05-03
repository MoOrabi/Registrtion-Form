package com.registrationform.service;

import static org.mockito.Mockito.verify;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;

import javax.swing.ImageIcon;

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
import org.springframework.web.multipart.MultipartFile;

import com.registrationform.entity.User;
import com.registrationform.exception.BadRequest;
import com.registrationform.repository.PersonRepository;
import com.registrationform.service.PersonService;

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
	void checkIfPersonIsAdded() throws IOException {
		User person = new User("Mohammed","Orabi",18,3,"12345678912345", Files.readAllBytes((new File("E:\\Eclipse_projects\\Registrtion-Form\\linkedincover.jpg")).toPath()),"Alarqam", "01153562201");
		personServiceUnderTest.addPerson(person);
		ArgumentCaptor<User> personArgumentCaptor = 
				ArgumentCaptor.forClass(User.class);
		verify(personRepository).save(personArgumentCaptor.capture());
		User capturedPerson = personArgumentCaptor.getValue();
		assertThat(capturedPerson).isEqualTo(person);
		
	}
	
	@Test
	void willThrowWhenIdIsDuplicated() throws IOException {
		User person = new User("Mohammed","Orabi",18,3,"12345678912345", Files.readAllBytes((new File("E:\\Eclipse_projects\\Registrtion-Form\\linkedincover.jpg")).toPath()),"Alarqam", "01153562201");
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
	
	 

		
}
