package com.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table

public class Students {

	
	@Id
	@Column(nullable = false)
	private String rollno;
	
	@Column
	private String name;
	
	@Column
	private String branch;
	
	@Column
	private String year;
	
	@Column
	private String password;

	public String getRollno() {
		return rollno;
	}

	public void setRollno(String rollno) {
		this.rollno = rollno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Students() {
		super();
	}

	public Students(String rollno, String name, String branch, String year, String password) {
		this.rollno = rollno;
		this.name = name;
		this.branch = branch;
		this.year = year;
		this.password = password;
	}
	
	
	
	
	
}
