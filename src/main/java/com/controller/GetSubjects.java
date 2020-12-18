package com.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.DAO.CourseSubMapping;
import com.DAO.SessionUtil;

@Path("/getsubjects")
public class GetSubjects {

	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubjects(String incomingData) throws Exception {
		String res = null;

		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		System.out.println("inside user");

		/*
		 * CourseSubMapping s=new CourseSubMapping("CSE","AI"); session.save(s);
		 * tx.commit();
		 */

		// Query query = session.createQuery("from Users WHERE
		// username="+itemEntry.username+"AND password="+itemEntry.password
		// +"");
		String hql = "FROM CourseSubMapping WHERE coursename= :coursename";

		// String hql = "delete from Employee where id= :id";
		Query query = session.createQuery(hql);

		query.setString("coursename", Sregister.s1.getBranch());
		System.out.println(Sregister.s1.getBranch());
		System.out.println("hiiii above");
		List<CourseSubMapping> subjects = query.list();
		System.out.println("hiiii below");

		// System.out.println(rides.size());
		int count = 0;

		JSONArray objArray = new JSONArray();

		String ocoursename = null, osubjects;
		for (CourseSubMapping next : subjects) {
			JSONObject obj = new JSONObject();

			ocoursename = next.getCoursename();
			osubjects = next.getSubname();
			System.out.println(ocoursename);
			System.out.println(osubjects);

			obj.put("coursename", ocoursename);
			obj.put("subname", osubjects);
			objArray.put(obj);

		}

		/*
		 * UsersDAO ud1 = new UsersDAO();
		 * ud1.checkUser(session,itemEntry.username,itemEntry.password);
		 */

		session.close();
		res = objArray.toString();
		return Response.ok(res).build();
	}
}
