/**
 * 
 */
package com.ad3bay0.empapp.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ad3bay0.empapp.entity.Person;
import com.ad3bay0.empapp.service.IPersonService;
import com.ad3bay0.empapp.util.CustomErrorType;

/**
 * @author Adebayo Adeniyan
 * 31 May 2017
 */
@RestController
@RequestMapping("/api")
public class RestApiController {
	
	public static final Logger logger = Logger.getLogger(RestApiController.class);
	
	@Autowired
	IPersonService personService;
	
	
/*
 * retrieve all users
 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/user/",method= RequestMethod.GET)
	public ResponseEntity<List<Person>> listAllPersons(){
		
		logger.info("----getting persons----");
		List<Person> persons = personService.getAllPersons();
		
		
		if(persons.isEmpty()){
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			
		}
		
		return new ResponseEntity<List<Person>>(persons, HttpStatus.OK);
		
		
		
	}
	
	/*
	 * retrieve a user
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/user/{id}",method = RequestMethod.GET)
     public ResponseEntity<?> getPerson(@PathVariable("id") int pid){
    	 
		
		logger.info("------getting person: "+pid+"----");
		
		
		Person person =  personService.findPersonById(pid);
		
		if(person == null){
			
			logger.info("Person id not found: "+pid);
			
			
			return new ResponseEntity(new CustomErrorType("Unable to get user with id "+pid),HttpStatus.NOT_FOUND);
			
			
		}
		
		return new ResponseEntity<Person>(person,HttpStatus.OK);
		
		
    	 
     }
	
	
	/*
	 * create a user
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/user/", method=RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody Person person, UriComponentsBuilder ucBuilder){
		
		logger.info("Creating person: "+person);
		
		if(personService.personExists(person.getUsername())){
			
			logger.info("unable to create person: user already exist! "+person.getUsername());
			
			return new ResponseEntity(new CustomErrorType("Unable to create user, "+person.getUsername()+"already exists!"),HttpStatus.CONFLICT);
			
			
		}
		
		personService.addPerson(person);
		
		
		HttpHeaders headers =  new HttpHeaders();
		
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(person.getPid()).toUri());
		
		return new ResponseEntity<String>(headers,HttpStatus.CREATED);
	}

	
	/*
	 * update user
	 * 
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/user/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") int pid,@RequestBody Person person){
		
		logger.info("updating user with id: "+pid);
		
		
		Person currrentPerson = personService.findPersonById(pid);
		
		if(currrentPerson == null){
			
			logger.info("unable to update user with id: "+pid);
			
			return new ResponseEntity(new CustomErrorType("unable to update user with id: "+pid),HttpStatus.NOT_FOUND);
			
		}
		
		currrentPerson.setAge(person.getAge());
		currrentPerson.setCity(person.getCity());
		currrentPerson.setGender(person.getGender());
		
		personService.updatePerson(currrentPerson);
		
		return new ResponseEntity<Person>(currrentPerson,HttpStatus.OK);
		
		
		
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") int pid){
		
		logger.info("fetching & deleting person id "+pid);
		
		Person person =  personService.findPersonById(pid);
		
		
		if(person == null){
			
			logger.info("Unable to delete person: "+pid);
			return new ResponseEntity(new CustomErrorType("Unable to delete person: "+pid),HttpStatus.NOT_FOUND);
			
		}
		
		personService.deletePerson(pid);
		
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
		
		
	}

}
