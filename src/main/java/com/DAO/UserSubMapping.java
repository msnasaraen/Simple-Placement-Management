package com.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table


public class UserSubMapping {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String rollno;
	
	@Column
	private String coursename;
	
	@Column 
	private String subname;

	public UserSubMapping() {
		super();
	}

	public UserSubMapping(String rollno, String coursename, String subname) {
		this.rollno = rollno;
		this.coursename = coursename;
		this.subname = subname;
	}

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getSubname() {
		return subname;
	}

	public void setSubname(String subname) {
		this.subname = subname;
	}


	
	
	
}
