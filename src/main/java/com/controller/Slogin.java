package com.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.DAO.SessionUtil;
import com.DAO.Students;

@Path("/slogin")

public class Slogin {

	// private static final Logger logger = Logger.getLogger(Slogin.class);

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(String incomingData) throws Exception {
		String res = null;
		JSONObject obj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		user itemEntry = mapper.readValue(incomingData, user.class);
		Session session = SessionUtil.getSession();
		session.beginTransaction();

		System.out.println(incomingData);
		System.out.println(itemEntry.rollno);
		System.out.println(itemEntry.password);

		// Query query = session.createQuery("from Users WHERE
		// username="+itemEntry.username+"AND password="+itemEntry.password
		// +"");

		String table = "Students";
		String s1 = "FROM " + table;

		String s2 = " WHERE rollno= :rollno AND password= :password";

		String hql = s1 + s2;
		// String hql = "delete from Employee where id= :id";
		Query query = session.createQuery(hql);

		query.setString("rollno", itemEntry.rollno);
		query.setString("password", itemEntry.password);

		List<Students> students = query.list();
		System.out.println(students.size());
		System.out.println("br1");

		if (students.size() == 1) {
			System.out.println("br2");

			String oname = null, opassword = null, orollno = null, oyear = null, obranch = null;
			for (Students next : students) {
				oname = next.getName();
				orollno = next.getRollno();
				opassword = next.getPassword();
				oyear = next.getYear();
				obranch = next.getBranch();
			}

			System.out.println("Success");
			Sregister.s1 = new Students(orollno, oname, obranch, oyear, opassword);
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

class user {
	public String rollno;
	public String password;
}
