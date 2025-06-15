package com.cdac.acts.StudentManagementSystem;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.cdac.acts.DAO.CourseDAO;
import com.cdac.acts.DAO.StudentDAO;
import com.cdac.acts.Entity.Course;
import com.cdac.acts.config.ApplicationConfigurator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try(AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfigurator.class);
        	Scanner sc = new Scanner(System.in);) {
        	// Student Registeration
//        	System.out.println("Enter the Name of the Student: ");
//        	String name = sc.next();
//        	System.out.println("Enter the Email of the Student: ");
//        	String email = sc.next();
//        	
//        	// Add a course
//        	System.out.println("Enter the Course Name: ");
//        	String courseName = sc.next();
//        	sc.nextLine();
//        	System.out.println("Enter the Course Description: ");
//        	String courseDesc = sc.nextLine();
//        	
        	StudentDAO stud = (StudentDAO)context.getBean("studentDAO");
        	CourseDAO cd = (CourseDAO) context.getBean("courseDAO");
        	
        	cd.deleteCourse(1);
        	
//        	boolean updateStatus = cd.updateCourse(1, "I am a english course");
//        	if(updateStatus) {
//        		System.out.println("Updation Successfully!");
//            	Iterator<Course> iter = cd.listAllCourses();
//            	while(iter.hasNext()) {
//            		Course c = iter.next();
//            		System.out.println(c);
//            	}
//        	}else {
//        		System.out.println("No updation");
//        	}
        	
//        	Iterator<Course> iter = cd.listAllCourses();
//        	while(iter.hasNext()) {
//        		Course c = iter.next();
//        		System.out.println(c);
//        	}
//        	
//        	stud.enrollInCourse(1, 2);
//        	System.out.println("successfully enroll in course");
//        	
//        	Set<Course> set = stud.getStudentCourses(1);
//        	if(set==null) {
//        		System.out.println("Not Courses found for the student");
//        	}else {
//        		set.forEach(System.out::println);
//        	}
        	
//        	boolean statusStudent = stud.registerStudent(name, email);
//        	boolean statusCourse = cd.addCourse(courseName, courseDesc);
//        	if(statusCourse) {
//        		System.out.println("Course registered successfully!");
//        	}else {
//        		System.out.println("Do error handling viro!");
//        	}
        }
    }
}
