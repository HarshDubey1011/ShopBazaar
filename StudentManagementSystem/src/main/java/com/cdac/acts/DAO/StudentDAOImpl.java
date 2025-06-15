package com.cdac.acts.DAO;

import java.util.Collections;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.cdac.acts.Entity.Course;
import com.cdac.acts.Entity.Student;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	SessionFactory hibernateFactory;
	
	Session hibernateSession;
	
	@PostConstruct
	public void initializeStudentDAO() {
		hibernateSession = hibernateFactory.openSession();
	}
	
	@PreDestroy
	public void releaseStudentDAO() {
		hibernateSession.close();
	}

	@Override
	public boolean registerStudent(String name, String email) {
		// TODO Auto-generated method stub
		Student stud = new Student(name,email);
		Transaction tx = hibernateSession.beginTransaction();
		hibernateSession.persist(stud);
		tx.commit();
		return true;
	}

	@Override
	public void enrollInCourse(int studentId, int courseId) {
		// TODO Auto-generated method stub
		Student student = hibernateSession.find(Student.class, studentId);
		Course course = hibernateSession.find(Course.class, courseId);
		
		if(student!=null && course!=null) {
			Transaction tx = hibernateSession.beginTransaction();
			student.getCourses().add(course);
			course.getStudent().add(student);
			tx.commit();
			System.out.println("Enrolled in a course successfully!");
		}else {
			System.out.println("No Student or Course Found");
		}
	}

	@Override
	public Set<Course> getStudentCourses(int studentId) {
		// TODO Auto-generated method stub
		Student stud = hibernateSession.find(Student.class, studentId);
		if(stud!=null) {
			return stud.getCourses();
		}
		return Collections.emptySet();
		
	}

}
