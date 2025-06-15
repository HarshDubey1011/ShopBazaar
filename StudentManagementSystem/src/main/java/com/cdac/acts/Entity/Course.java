package com.cdac.acts.Entity;

import java.util.HashSet;
import java.util.Set;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="course")
public class Course {
	@Id
	@GeneratedValue(generator="increment")
	@Column(name="cid")
	private int courseId;
	
	@Column(name="courseName")
	private String courseName;
	@Column(name="courseDescription")
	private String courseDescription;
	
	@ManyToMany(mappedBy="courses")
	private Set<Student> student = new HashSet<>();;

	
	
	public Course() {
		super();
	}

	public Course(String courseName, String courseDescription) {
		super();
		
		this.courseName = courseName;
		this.courseDescription = courseDescription;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseDescription() {
		return courseDescription;
	}

	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}

	public Set<Student> getStudent() {
		return student;
	}

	public void setStudent(Set<Student> student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", courseDescription="
				+ courseDescription + "]";
	}
	
	
	
}
