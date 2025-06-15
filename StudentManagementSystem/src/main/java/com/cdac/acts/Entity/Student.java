package com.cdac.acts.Entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="student")
public class Student {
	@Id
	@GeneratedValue(generator="increment")
	@Column(name="id")
	private int studentId;
	
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	
	@ManyToMany
	@JoinTable(
			name="student_course",
			joinColumns = @JoinColumn(name="id"),
			inverseJoinColumns = @JoinColumn(name="cid")
			)
	
	private Set<Course> courses = new HashSet<>();
	
	public Student() {
		super();
	}

	public Student(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}
