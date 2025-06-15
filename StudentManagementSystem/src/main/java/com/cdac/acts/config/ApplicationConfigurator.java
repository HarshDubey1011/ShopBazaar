package com.cdac.acts.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.stereotype.Component;

import com.cdac.acts.DAO.CourseDAO;
import com.cdac.acts.DAO.CourseDAOImpl;
import com.cdac.acts.DAO.StudentDAO;
import com.cdac.acts.DAO.StudentDAOImpl;
import com.cdac.acts.Entity.Course;
import com.cdac.acts.Entity.Student;

@org.springframework.context.annotation.Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfigurator {
	@Autowired
	AbstractEnvironment env;
	
	@Bean
	public SessionFactory hibernateFactory(Configuration hibernateConfiguration) {
		return hibernateConfiguration.buildSessionFactory();
	}
	
	@Bean
	public Configuration hibernateConfiguration() {
		Configuration hibernateConfig = new Configuration();
		Properties hibernateProps = new Properties();
		hibernateProps.setProperty("hibernate.connection.driver_class", env.getProperty("hibernate.connection.driver_class"));
		hibernateProps.setProperty("hibernate.connection.url", env.getProperty("hibernate.connection.url"));
		hibernateProps.setProperty("hibernate.connection.username", env.getProperty("hibernate.connection.username"));
		hibernateProps.setProperty("hibernate.connection.password", env.getProperty("hibernate.connection.password"));
		hibernateProps.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProps.setProperty("hibernate.hbm2dll.auto", env.getProperty("hibernate.hbm2dll.auto"));
		hibernateProps.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
		
		hibernateConfig.addProperties(hibernateProps);
		hibernateConfig.addAnnotatedClass(Course.class);
		hibernateConfig.addAnnotatedClass(Student.class);
		return hibernateConfig;
	}
	
	@Bean
	public CourseDAO courseDAO() {
		return new CourseDAOImpl();
	}
	
	@Bean
	public StudentDAO studentDAO() {
		return new StudentDAOImpl();
	}
}
