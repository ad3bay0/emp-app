/**
 * 
 */
package com.ad3bay0.empapp.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ad3bay0.empapp.entity.Person;

/**
 * @author Adebayo Adeniyan
 * 26 May 2017
 */

@Transactional
@Repository
public class PersonDao implements IPersonDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		
		return sessionFactory.getCurrentSession();
	}

	public Person getPersonById(int uid){		
		Person p = (Person)getSession().get(Person.class, uid);	
		return p;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Person> getAllPersons(){
		String hql = "FROM Person as p ORDER BY p.id";
		return getSession().createQuery(hql).list();
	}
	

	public void addPerson(Person person){
		
		getSession().persist(person);
		
	}
	
	public void updatePerson(Person person){
		Person p = getPersonById(person.getPid());
		p.setUsername(person.getUsername());
		p.setPassword(person.getPassword());
		p.setAge(person.getAge());
		p.setGender(person.getGender());
		p.setCity(person.getCity());	
		getSession().merge(p);
	}

	public boolean personExists(String username){	
		
		Criteria crit =  getSession().createCriteria(Person.class);
		crit.add(Restrictions.eq("username", username));
		Person p  = (Person)crit.uniqueResult();
		
		if(p==null){
			
			return false;
			
		}else{
			
			return true;	
		}	
	}
	
	public void deletePerson(int pid){
		
		getSession().delete(getPersonById(pid));
		
	}
	
}
