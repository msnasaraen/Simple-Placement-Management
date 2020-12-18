package com.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.DAO.SCGPA;
import com.DAO.SessionUtil;

@Path("/showdetails")
public class Sdetails {

	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateStudentsDetails(String incomingData) throws Exception {
		String res = null;
		Session session = SessionUtil.getSession();
		session.beginTransaction();

		System.out.println(incomingData);

		Sregister.s1.getBranch();
		String rollno = Sregister.s1.getRollno();

		String s1 = "FROM " + "SCGPA";

		String s2 = " WHERE rollno= :rollno";

		String hql = s1 + s2;
		Query query = session.createQuery(hql);

		query.setString("rollno", rollno);

		List<SCGPA> students = query.list();
		System.out.println(students.size());
		System.out.println("br1");

		JSONArray objArray = new JSONArray();
		String orollno = null;
		float ocgpa = 0;
		if (students.size() == 1) {
			System.out.println("br2");

			for (SCGPA next : students) {
				orollno = next.getRollno();
				ocgpa = next.getCgpa();
			}

			JSONObject obj = new JSONObject();

			System.out.println("Success");
			session.close();
			obj.put("rollno", orollno);
			obj.put("cgpa", ocgpa);
			objArray.put(obj);

		}

		else {
			JSONObject obj = new JSONObject();

			System.out.println("br3");
			session.close();
			obj.put("status", "failure");
			objArray.put(obj);

		}

		res = objArray.toString();
		return Response.ok(res).build();

		// return null;

	}

}
