/**
 * 
 */
package com.ad3bay0.empapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Adebayo Adeniyan
 * 30 May 2017
 */
@Controller
public class WelcomeController {
	
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String index(ModelMap model){
		
		
		return "index";
	}
	

}
