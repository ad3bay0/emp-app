/**
 * 
 */
package com.ad3bay0.empapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ad3bay0.empapp.dao.IPersonDao;
import com.ad3bay0.empapp.entity.Person;

/**
 * @author Adebayo Adeniyan
 * 30 May 2017
 */

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonDao personDao;
	
	public List<Person> getAllPersons() {
		
		return personDao.getAllPersons(); 
	}

	/* (non-Javadoc)
	 * @see com.ad3bay0.empapp.service.IPersonService#findPersonById(int)
	 */
	public Person findPersonById(int pid) {
		
		return personDao.getPersonById(pid);
	}

	/* (non-Javadoc)
	 * @see com.ad3bay0.empapp.service.IPersonService#addPerson(com.ad3bay0.empapp.entity.Person)
	 */
	public synchronized boolean addPerson(Person person) {
		
		if(personDao.personExists(person.getUsername())){
			
			return false;
			
		}else{
			
			personDao.addPerson(person);
			
			return true;
		}
		
		
	}

	/* (non-Javadoc)
	 * @see com.ad3bay0.empapp.service.IPersonService#updatePerson(com.ad3bay0.empapp.entity.Person)
	 */
	public void updatePerson(Person person) {
		
		personDao.updatePerson(person);

	}

	/* (non-Javadoc)
	 * @see com.ad3bay0.empapp.service.IPersonService#deletePerson(int)
	 */
	public void deletePerson(int pid) {
	
		personDao.deletePerson(pid);

	}

}
