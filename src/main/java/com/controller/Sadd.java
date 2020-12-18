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
import com.DAO.SCGPA;
import com.DAO.SessionUtil;

@Path("/adddetails")
public class Sadd {

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStudentDetails(String incomingData) throws Exception {
		String res = null;

		new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		ItemEntry1 itemEntry = mapper.readValue(incomingData, ItemEntry1.class);

		/*
		 * System.out.println(itemEntry.ai);
		 * System.out.println(itemEntry.database);
		 * System.out.println(itemEntry.dsa);
		 * System.out.println(itemEntry.oops);
		 * System.out.println(itemEntry.cgpa);
		 */
		Session session = SessionUtil.getSession();

		Transaction tx1 = session.beginTransaction();

		String hql = "delete from SCGPA where rollno= :rollno";
		Query query = session.createQuery(hql);

		query.setString("rollno", Sregister.s1.getRollno());

		int rowCount = query.executeUpdate();
		System.out.println("Rows affected: " + rowCount);
		tx1.commit();

		/*
		 * Transaction tx2 = session.beginTransaction();
		 * 
		 * String hql1 = "delete from UserSubMapping where rollno= :rollno";
		 * Query query1= session.createQuery(hql1);
		 * 
		 * query1.setString("rollno",Sregister.s1.getRollno());
		 * 
		 * int rowCount1 = query1.executeUpdate(); System.out.println(
		 * "Rows affected: " + rowCount1); tx2.commit();
		 */

		Transaction tx = session.beginTransaction();
		SCGPA c1 = new SCGPA(itemEntry.cgpa, Sregister.s1.getRollno());
		session.save(c1);
		tx.commit();

		System.out.println(incomingData);
		/*
		 * for (int i = 0; i < 4; i++) {
		 * 
		 * System.out.println("dfsa"); if(i==0 && itemEntry.ai.equals("yes")) {
		 * Transaction tx4 = session.beginTransaction();
		 * 
		 * System.out.println("cahmges");
		 * 
		 * UserSubMapping u1 = new
		 * UserSubMapping(Sregister.s1.getRollno(),Sregister.s1.getBranch(),"AI"
		 * ); UserSubMappingDAO ub1=new UserSubMappingDAO();
		 * System.out.println("cahmges"); obj.put("status", "Success"); res =
		 * obj.toString(); System.out.println("inside session");
		 * ub1.addSubjects(session, u1); tx4.commit();
		 * 
		 * } else if(i==1 && itemEntry.dsa.equals("yes")) { Transaction tx4 =
		 * session.beginTransaction();
		 * 
		 * UserSubMapping u1 = new
		 * UserSubMapping(Sregister.s1.getRollno(),Sregister.s1.getBranch(),
		 * "DSA"); UserSubMappingDAO ub1=new UserSubMappingDAO();
		 * System.out.println("cahmges"); obj.put("status", "Success"); res =
		 * obj.toString(); System.out.println("inside session");
		 * ub1.addSubjects(session, u1); tx4.commit();
		 * 
		 * } else if(i==2 && itemEntry.database.equals("yes")) { Transaction tx4
		 * = session.beginTransaction();
		 * 
		 * UserSubMapping u1 = new
		 * UserSubMapping(Sregister.s1.getRollno(),Sregister.s1.getBranch(),
		 * "DATABASE"); UserSubMappingDAO ub1=new UserSubMappingDAO();
		 * System.out.println("cahmges"); obj.put("status", "Success"); res =
		 * obj.toString(); System.out.println("inside session");
		 * ub1.addSubjects(session, u1); tx4.commit();
		 * 
		 * }
		 * 
		 * else if(i==3 && itemEntry.oops.equals("yes")) { Transaction tx4 =
		 * session.beginTransaction();
		 * 
		 * UserSubMapping u1 = new
		 * UserSubMapping(Sregister.s1.getRollno(),Sregister.s1.getBranch(),
		 * "OOPS"); UserSubMappingDAO ub1=new UserSubMappingDAO();
		 * System.out.println("cahmges"); obj.put("status", "Success"); res =
		 * obj.toString(); System.out.println("inside session");
		 * ub1.addSubjects(session, u1); tx4.commit();
		 * 
		 * }
		 * 
		 * 
		 * }
		 */
		session.close();
		return Response.ok(res).build();
	}
}

class ItemEntry1 {
	public float cgpa;

}
