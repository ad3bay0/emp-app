/**
 * 
 */
package com.ad3bay0.empapp.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Adebayo Adeniyan
 * 24 May 2017
 */

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:database.properties")
public class DBConfig {
	
	@Autowired
	private Environment env;
	
	
	@Bean
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("database.driver"));
		dataSource.setUrl(env.getProperty("database.url"));
		dataSource.setUsername(env.getProperty("database.username"));
		dataSource.setPassword(env.getProperty("database.password"));
		return dataSource;
		
	}
	
	private Properties hibernateProperties(){
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		
		return properties;
		
		
	}
	
	@Bean
	public SessionFactory sessionFactory(){
		
		LocalSessionFactoryBean lsfb = new LocalSessionFactoryBean();
		lsfb.setDataSource(getDataSource());
		lsfb.setPackagesToScan("com.ad3bay0.empapp.entity");
		lsfb.setHibernateProperties(hibernateProperties());
		
		try {
			lsfb.afterPropertiesSet();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		return lsfb.getObject();
		
	}
	
	@Bean
	public HibernateTransactionManager hibTransMan(){
		
		return new HibernateTransactionManager(sessionFactory());
	}
	


}
