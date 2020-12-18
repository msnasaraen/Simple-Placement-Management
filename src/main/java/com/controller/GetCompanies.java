package com.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.DAO.CompanyDetails;
import com.DAO.SCGPA;
import com.DAO.SessionUtil;

@Path("/getcompanies")

public class GetCompanies {

	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanies(String incomingData) throws Exception {
		String res = null;
		new JSONObject();
		Session session = SessionUtil.getSession();

		System.out.println(incomingData);

		Transaction tx5 = session.beginTransaction();
		String hql = "FROM SCGPA WHERE rollno= :rollno";

		Query query = session.createQuery(hql);

		query.setString("rollno", Sregister.s1.getRollno());

		List<SCGPA> scgpa = query.list();
		System.out.println(scgpa.size());
		System.out.println("br1");

		System.out.println("br2");
		// JSONArray objArray = new JSONArray();

		float oocgpa = 0;
		for (SCGPA next : scgpa) {

			// JSONObject obj1 = new JSONObject();

			next.getRollno();
			oocgpa = next.getCgpa();
			System.out.println(next.getRollno() + " " + next.getCgpa());
		}
		tx5.commit();

		Transaction tx4 = session.beginTransaction();
		hql = "FROM CompanyDetails WHERE cgpa<= :cgpa AND branch= :branch";

		query = session.createQuery(hql);

		query.setFloat("cgpa", oocgpa);
		query.setString("branch", Sregister.s1.getBranch());

		JSONArray objArray = new JSONArray();

		List<CompanyDetails> students = query.list();
		System.out.println(students.size());
		System.out.println("br1");

		System.out.println("br2");

		String oname = null;
		float ocgpa = 0;
		for (CompanyDetails next : students) {

			JSONObject obj1 = new JSONObject();
			oname = next.getName();
			ocgpa = next.getCgpa();
			obj1.put("name", next.getName());
			obj1.put("cgpa", next.getCgpa());
			obj1.put("branch", next.getBranch());
			obj1.put("date", next.getDate());
			objArray.put(obj1);
		}
		System.out.println(oname + " " + ocgpa);
		System.out.println("Success");

		tx4.commit();

		session.close();
		res = objArray.toString();
		return Response.ok(res).build();
	}
}
