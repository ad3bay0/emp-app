/**
 * 
 */
package com.ad3bay0.empapp.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author Adebayo Adeniyan
 * 24 May 2017
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[]{AppConfig.class};
	}

	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return null;
	}


	@Override
	protected String[] getServletMappings() {
		
		return new String[]{"/"};
	}



}
