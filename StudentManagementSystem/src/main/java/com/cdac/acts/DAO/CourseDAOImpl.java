package com.cdac.acts.DAO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdac.acts.Entity.Course;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class CourseDAOImpl implements CourseDAO {
	@Autowired
	SessionFactory hibernateFactory;
	
	Session hibernateSession;
	
	@PostConstruct
	public void initializeCourseDAO() {
		hibernateSession = hibernateFactory.openSession();
	}
	
	@PreDestroy
	public void releaseCourseDAO() {
		hibernateSession.close();
	}
	

	@Override
	public boolean addCourse(String name, String description) {
		// TODO Auto-generated method stub
		Course c = new Course(name,description);
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.persist(c);
		tx.commit();
		return true;
	}

	@Override
	public boolean updateCourse(int id, String newDescription) {
		// TODO Auto-generated method stub
		Course c = hibernateSession.find(Course.class, id);
		if(c!=null) {
			Transaction tx = hibernateSession.beginTransaction();
			c.setCourseDescription(newDescription);
			hibernateSession.merge(c);
			tx.commit();
			return true;
		}
		return false;
	
	}

	@Override
	public void deleteCourse(int id) {
		// TODO Auto-generated method stub
		Course c = hibernateSession.find(Course.class, id);
		if(c!=null) {
			Transaction tx = hibernateSession.beginTransaction();
			hibernateSession.remove(c);
			tx.commit();
			System.out.println("Delete successfully!");
		}else {
			System.out.println("Course not found!");
		}
		

	}

	@Override
	public Iterator<Course> listAllCourses() {
		// TODO Auto-generated method stub
		List<Course> courseList = new ArrayList<>();
		Query<Course> course = hibernateSession.createQuery("from Course",Course.class);
		courseList = course.getResultList();
		return courseList.iterator();
	}

}
