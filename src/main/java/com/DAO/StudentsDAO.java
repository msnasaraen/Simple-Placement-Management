package com.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class StudentsDAO {


	
	public void addStudents(Session session, Students bean) {
		System.out.println("inside user");

		Students s=new Students(bean.getRollno(),bean.getName(),bean.getBranch(),bean.getYear(),bean.getPassword());
		session.save(s);
	}
	
	public int checkUser(Session session,String username,String Password) {
		System.out.println("inside user login");
		String hql = "SELECT * FROM Users WHERE username= :username AND password= :password";
		// String hql = "delete from Employee where id= :id";
		Query query = session.createQuery(hql);

		// query.setInteger("id", 1);
		query.setString("username",username);
		query.setString("password", Password);

		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		if(rowCount == 1)
		{
			System.out.println("success");
		}
		return rowCount;
		/*Users u = new Users(bean.getName(), bean.getUsername(), bean.getPassword(), bean.getMobile(), bean.getEmail(),
				bean.getOrganization());
		session.save(u);*/
	}

}
