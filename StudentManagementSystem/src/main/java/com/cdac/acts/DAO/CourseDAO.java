package com.cdac.acts.DAO;

import java.util.Iterator;

import com.cdac.acts.Entity.Course;

public interface CourseDAO {
	boolean addCourse(String name, String description);
	boolean updateCourse(int id, String newDescription);
	void deleteCourse(int id);
	Iterator<Course> listAllCourses();
}
