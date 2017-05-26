/**
 * 
 */
package com.ad3bay0.empapp.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author Adebayo Adeniyan
 * 26 May 2017
 */
@SuppressWarnings("serial")
@Entity
@Table(name="person")
public class Person implements Serializable {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="pid")
	private int pid;
	
	@Size(min=5,max=20)
	@Column(name="username")
	private String username;
	
	@Size(min=8,max=15)
	@Column(name="password")
	private String password;
	
	@Min(18)
	@Max(100)
	@Column(name="age")
	private int age;
	
	@NotNull
	@Column(name="gender")
	private String gender;
	
	
	@NotEmpty
	@Column(name="city")
	private String city;


	public int getPid() {
		return pid;
	}


	public void setPid(int pid) {
		this.pid = pid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}
	
	private static Map<String, String> cityMap =  new HashMap<String, String>();
	
	
	private static Map<String,String> genderOptions = new HashMap<String, String>();
	
	
	public static Map<String, String> getPersonMap (){
		
		if(cityMap.size() == 0 ){
			
			cityMap.put("Abuja", "Abuja");
			cityMap.put("Lagos", "Lagos");
			
		}
		return cityMap;
	}
	
	
	public static Map<String, String> getGenderOptions(){
		
		if(genderOptions.size() ==0){
			genderOptions.put("M", "Male");
			genderOptions.put("F", "Female");
		}
		
		return genderOptions;
	}
	
	
	
	
	
	
	
	
	
	

}
