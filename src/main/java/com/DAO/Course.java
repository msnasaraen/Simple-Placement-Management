package com.DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table


public class Course {
	
	@Id
	private String coursename;

	public Course(String coursename) {
		this.coursename = coursename;
	}

	public Course() {
		super();
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	
	
}
