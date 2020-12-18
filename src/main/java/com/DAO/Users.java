package com.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Users {
		
	@Column
	private String name;
	
	@Id
	@Column(nullable = false)
	private String username;
	
	@Column
	private String password;
	
	@Column
	private String mobile;
	
	@Column
	private String email;
	 
	@Column
	private String organization;

	/*@OneToMany(mappedBy="users",cascade=CascadeType.REMOVE)
    private List<Users> users = new ArrayList<Users>();

	
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	
	public Users() {
		super();
	}

	public Users(String name, String username, String password, String mobile, String email, String organization) {
		
		this.name = name;
		this.username = username;
		this.password = password;
		this.mobile = mobile;
		this.email = email;
		this.organization = organization;
	}
	
	
}
