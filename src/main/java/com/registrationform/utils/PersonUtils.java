package com.registrationform.utils;

import com.registrationform.entity.User;
import com.registrationform.exception.BadRequest;

public class PersonUtils {

	public static void checkPersonIsValid(User person) {
		if (person.getNationalId().length()>14||person.getNationalId().length()<14)
                throw new BadRequest("Wrong Id");
        if (person.getFirstName()==null||person.getLastName()==null||
        		person.getLevel()==0||person.getImage()==null||
        		person.getDarName()==null)
            throw new BadRequest("Enter All required data");
	}
}
