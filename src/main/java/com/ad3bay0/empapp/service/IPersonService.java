/**
 * 
 */
package com.ad3bay0.empapp.service;

import java.util.List;

import com.ad3bay0.empapp.entity.Person;

/**
 * @author Adebayo Adeniyan
 * 30 May 2017
 */
public interface IPersonService {

	List<Person> getAllPersons();
	
	Person findPersonById(int pid);
	
	boolean addPerson(Person person);
	
	void updatePerson(Person person);
	
	void deletePerson(int pid); 
	
	
}
