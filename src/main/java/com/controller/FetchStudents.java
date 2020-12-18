package com.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.DAO.CompanyDetails;
import com.DAO.SCGPA;
import com.DAO.SessionUtil;
import com.DAO.Students;

@Path("/fetch")

public class FetchStudents {

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStudents(String incomingData) throws Exception {
		String res = null;
		new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		com itemEntry = mapper.readValue(incomingData, com.class);
		Session session = SessionUtil.getSession();

		System.out.println(incomingData);
		System.out.println(itemEntry.cname);

		Transaction tx4 = session.beginTransaction();
		String hql = "FROM CompanyDetails WHERE name= :name AND branch= :branch";

		Query query = session.createQuery(hql);

		query.setString("name", itemEntry.cname);
		query.setString("branch", itemEntry.branch);
		List<CompanyDetails> students = query.list();
		System.out.println(students.size());
		System.out.println("br1");

		System.out.println("br2");

		String oname = null;
		float ocgpa = 0;
		int i = 0;
		for (CompanyDetails next : students) {
			oname = next.getName();
			ocgpa = next.getCgpa();
		}
		System.out.println(oname + " " + ocgpa);
		System.out.println("Success");

		tx4.commit();

		Transaction tx5 = session.beginTransaction();
		hql = "FROM SCGPA WHERE cgpa>= :cgpa";

		query = session.createQuery(hql);

		query.setFloat("cgpa", ocgpa);

		List<SCGPA> scgpa = query.list();
		tx5.commit();
		System.out.println(scgpa.size());
		System.out.println("br1");

		System.out.println("br2");
		JSONArray objArray = new JSONArray();

		String[] orollno = new String[scgpa.size()];
		float[] oocgpa = new float[scgpa.size()];
		i = 0;
		for (SCGPA next : scgpa) {

			orollno[i] = next.getRollno();
			oocgpa[i++] = next.getCgpa();
			System.out.println(next.getRollno() + " " + next.getCgpa());

			Transaction tx8 = session.beginTransaction();
			String get = "FROM Students where rollno= :rollno AND branch= :branch";
			Query query5 = session.createQuery(get);
			query5.setString("rollno", next.getRollno());
			query5.setString("branch", itemEntry.branch);
			List<Students> ss = query5.list();
			tx8.commit();
			System.out.println(ss.size());

			if (ss.size() == 1) {
				for (Students s11 : ss) {
					JSONObject obj1 = new JSONObject();
					System.out.println(s11.getRollno());
					System.out.println(s11.getName());
					System.out.println(s11.getBranch());
					System.out.println(next.getCgpa());
					obj1.put("rollno", s11.getRollno());
					obj1.put("name", s11.getName());
					obj1.put("branch", s11.getBranch());
					obj1.put("cgpa", next.getCgpa());
					objArray.put(obj1);
				}
			}
		}

		session.close();
		res = objArray.toString();
		return Response.ok(res).build();
	}
}

class com {
	public String cname;
	public String branch;

}
