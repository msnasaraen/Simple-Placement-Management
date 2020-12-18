package com.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.DAO.Admin;
import com.DAO.SessionUtil;

@Path("/alogin")

public class Alogin {

	public static boolean admin = false;

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response adminLogin(String incomingData) throws Exception {
		String res = null;
		JSONObject obj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		usera itemEntry = mapper.readValue(incomingData, usera.class);
		Session session = SessionUtil.getSession();

		System.out.println(incomingData);
		System.out.println(itemEntry.username);
		System.out.println(itemEntry.password);

		// Query query = session.createQuery("from Users WHERE
		// username="+itemEntry.username+"AND password="+itemEntry.password
		// +"");

		String table = "Admin";
		String s1 = "FROM " + table;
		session.beginTransaction();

		String s2 = " WHERE username= :rollno AND password= :password";

		String hql = s1 + s2;
		// String hql = "delete from Employee where id= :id";
		Query query = session.createQuery(hql);

		query.setString("rollno", itemEntry.username);
		query.setString("password", itemEntry.password);

		List<Admin> students = query.list();
		System.out.println(students.size());
		System.out.println("br1");

		if (students.size() == 1) {
			System.out.println("br2");

			for (Admin next : students) {
				next.getUsername();
				next.getPassword();
				Aregister.a1 = new Admin(itemEntry.username, itemEntry.password);
			}
			admin = true;
			System.out.println("Success");
			session.close();
			obj.put("status", "Success");
			res = obj.toString();
			return Response.ok(res).build();
		}

		else {
			System.out.println("br3");

			session.close();
			obj.put("status", "failure");
			res = obj.toString();
			return Response.ok(res).build();
		}
		/*
		 * UsersDAO ud1 = new UsersDAO();
		 * ud1.checkUser(session,itemEntry.username,itemEntry.password);
		 */

	}
}

class usera {
	public String username;
	public String password;
}
