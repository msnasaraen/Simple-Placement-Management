package com.DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table



public class CompanyDetails {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column 
	private String name;
	
	@Column 
	private String date;
	
	@Column 
	private String branch;
	
	@Column 
	private float cgpa;
	
	public CompanyDetails(String name, String date, String branch, float cgpa) {		this.name = name;
		this.date = date;
		this.branch = branch;
		this.cgpa = cgpa;
	}

	public CompanyDetails() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public float getCgpa() {
		return cgpa;
	}

	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}
}
