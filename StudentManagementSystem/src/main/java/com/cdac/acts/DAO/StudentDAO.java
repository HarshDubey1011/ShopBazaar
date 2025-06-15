package com.cdac.acts.DAO;

import java.util.Set;

import com.cdac.acts.Entity.Course;

public interface StudentDAO {
	boolean registerStudent(String name, String email);
	void enrollInCourse(int studentId, int courseId);
	Set<Course> getStudentCourses(int studentId);
}
