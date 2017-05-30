/**
 * 
 */
package com.ad3bay0.empapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.ad3bay0.empapp.entity.Person;
import com.ad3bay0.empapp.service.IPersonService;

/**
 * @author Adebayo Adeniyan
 * 30 May 2017
 */

@Controller
public class PersonController {
	
	@Autowired
	private IPersonService iPersonService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private LocaleResolver localeResolver;
	
	
	@RequestMapping(value="personForm")
	public ModelAndView user(){
		
		ModelAndView mv = new ModelAndView("personForm", "person", new Person());
		
		setPageData(mv.getModelMap());
		
		return mv;
		
		
	}
	
	
	@RequestMapping(value="personById")
	public String getPersonById(ModelMap model,HttpServletRequest request){
		
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		
		Person person =  iPersonService.findPersonById(pid);
		
		setPageData(model);
		
		model.addAttribute(person);
		
		return "personForm";
		
		
		
		
		
	}
	
	
	@RequestMapping(value="addPerson",method=RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") @Valid Person person, BindingResult result,ModelMap model,HttpServletRequest request){
		
		if(!result.hasErrors()){
			
			boolean flag = iPersonService.addPerson(person);
			
			if(flag){
				model.addAttribute(new Person());
				model.addAttribute("msg", getMsg("person.added",request));
				
			}else{
				model.addAttribute("msg", getMsg("person.duplicate",request));		
							
			}
						
		}
		
		setPageData(model);

		return "personForm";
		
	}
	
	@RequestMapping(value="updatePerson",method=RequestMethod.POST)
	public String updatePerson(@ModelAttribute("person") @Valid Person person,BindingResult result,ModelMap model,HttpServletRequest request){
		
		if(!result.hasErrors()){
			
			iPersonService.updatePerson(person);
			model.addAttribute(new Person());
			model.addAttribute("msg",getMsg("person.updated", request));
			
		}
		
		setPageData(model);
			
		return "personForm";
	}
	
	@RequestMapping(value="deletePerson")
	public String deletePerson(ModelMap model,HttpServletRequest request){
		
		int pid = Integer.parseInt(request.getParameter("pid"));
		iPersonService.deletePerson(pid);
		model.addAttribute(new Person());
		model.addAttribute("msg", getMsg("person.deleted", request));
		setPageData(model);
		
		return "personForm";
		
	}



	/**
	 * @param string 
	 * @param request
	 * @return
	 */
	private Object getMsg(String key, HttpServletRequest request) {
		
		return messageSource.getMessage(key, null, localeResolver.resolveLocale(request));
	}


	/**s
	 * @param modelMap
	 */
	private void setPageData(ModelMap model) {
		
		model.addAttribute("allPersons", iPersonService.getAllPersons());
		model.addAttribute("genderOptions", Person.getGenderOptions());
		model.addAttribute("cityMap", Person.getPersonMap());
		
	}

}
