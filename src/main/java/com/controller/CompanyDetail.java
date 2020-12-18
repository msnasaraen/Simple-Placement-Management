package com.controller;

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

import com.DAO.CompanyDetails;
import com.DAO.SessionUtil;

@Path("/company")
public class CompanyDetail {

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCompanyDetails(String incomingData) throws Exception {

		String res = null;

		if (Aregister.a1 != null) {
			System.out.println(incomingData);

			JSONObject obj = new JSONObject();
			ObjectMapper mapper = new ObjectMapper();
			cdetails itemEntry = mapper.readValue(incomingData, cdetails.class);

			System.out.println(itemEntry.cname);
			System.out.println(itemEntry.date);
			System.out.println(itemEntry.branch);
			// System.out.println(itemEntry.ai);
			/*
			 * System.out.println(itemEntry.database);
			 * System.out.println(itemEntry.dsa);
			 * System.out.println(itemEntry.oops);
			 */
			System.out.println(itemEntry.cgpa);
			Session session = SessionUtil.getSession();

			Transaction tx2 = session.beginTransaction();

			String hql1 = "delete from CompanyDetails where name= :name AND branch= :branch";
			Query query1 = session.createQuery(hql1);

			query1.setString("name", itemEntry.cname);
			query1.setString("branch", itemEntry.branch);
			int rowCount1 = query1.executeUpdate();
			System.out.println("Rows affected: " + rowCount1);
			tx2.commit();

			System.out.println(incomingData);

			Transaction tx4 = session.beginTransaction();
			CompanyDetails c1 = new CompanyDetails(itemEntry.cname, itemEntry.date, itemEntry.branch, itemEntry.cgpa);
			session.save(c1);
			System.out.println("cahmges");
			obj.put("status", "Success");
			res = obj.toString();
			System.out.println("inside session");
			tx4.commit();

			session.close();
		} else {

			JSONObject obj = new JSONObject();
			obj.put("status", "failure");
			res = obj.toString();
		}

		return Response.ok(res).build();

	}
}

class cdetails {
	public float cgpa;
	public String cname;
	public String date;
	public String branch;
}
