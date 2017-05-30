/**
 * 
 */
package com.ad3bay0.empapp.dao;

import java.util.List;

import com.ad3bay0.empapp.entity.Person;

/**
 * @author Adebayo Adeniyan
 * 26 May 2017
 */
public interface IPersonDao {

	/**
	 * @param uid
	 * @return the person object
	 */
	public Person getPersonById(int uid);

	/**
	 * @param person
	 */
	void addPerson(Person person);

	/**
	 * @param username
	 * @return
	 */
	boolean personExists(String username);
	
	List<Person> getAllPersons();
	
	void updatePerson(Person person);
	
	void deletePerson(int pid);

}
