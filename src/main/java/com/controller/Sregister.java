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
import com.DAO.StudentsDAO;

@Path("/sregister")
public class Sregister {

	private static final Logger logger = Logger.getLogger(Sregister.class);

	public static String rootUser = null;
	public static Students s1;

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(String incomingData) throws Exception {

		String res = null;
		JSONObject obj = new JSONObject();

		System.out.println(incomingData);
		ObjectMapper mapper = new ObjectMapper();
		studentDetails u = mapper.readValue(incomingData, studentDetails.class);
		Session session = SessionUtil.getSession();
		Transaction tx = session.beginTransaction();

		String hql = "FROM Students";
		Query query = session.createQuery(hql);
		System.out.println("hiiii above");
		List<Students> users = query.list();
		System.out.println("hiiii below");

		System.out.println("br1");

		for (Students next : users) {
			System.out.println("br2");

			if (u.rollno.equals(next.getRollno())) {
				System.out.println("br3");

				obj.put("status", "failure");
				res = obj.toString();
				session.close();

				return Response.ok(res).build();
			}
		}

		logger.debug("Registered");

		System.out.println("br4");

		s1 = new Students(u.rollno, u.name, u.branch, u.year, u.password);
		StudentsDAO ud1 = new StudentsDAO();
		ud1.addStudents(session, s1);
		tx.commit();
		obj.put("status", "Success");
		res = obj.toString();
		session.close();
		return Response.ok(res).build();
	}
}

class studentDetails {
	public String rollno;
	public String name;
	public String password;
	public String branch;
	public String year;
}
