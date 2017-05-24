/**
 * 
 */
package com.ad3bay0.empapp.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author Adebayo Adeniyan
 * 24 May 2017
 */
@Configuration
@ComponentScan("com.ad3bay0")
@Import(DBConfig.class)
@EnableWebMvc
public class AppConfig extends WebMvcConfigurerAdapter {
	
	
	@Bean
	public InternalResourceViewResolver viewResolver(){
		
		InternalResourceViewResolver resolver =  new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/view/");
		resolver.setSuffix(".jsp");
		return resolver;
		
	}
	
	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		LocaleChangeInterceptor interceptor =  new LocaleChangeInterceptor();
		interceptor.setParamName("mylocale");
		registry.addInterceptor(interceptor);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app-resources/**").addResourceLocations("/resource/");
	}
	

}
