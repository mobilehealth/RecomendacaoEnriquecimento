package mobilehealth.core.controller;

import java.util.ArrayList;
import java.util.List;

import mobilehealth.core.dao.PersonDAO;
import mobilehealth.core.domain.Person;

public class PersonController {
	
	private PersonDAO personDAO;
	
	public PersonController() {
		personDAO = new PersonDAO();
	}
	
	public List<Person> getAllPersons() {
		List<Person> allPersons = new ArrayList<Person>();
		allPersons = personDAO.findAll();
		return allPersons;
	}
}
