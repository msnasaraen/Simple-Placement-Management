package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.DAO.Admin;
import com.DAO.SessionUtil;

@Path("/aregister")

public class Aregister {

	public static Admin a1 = null;

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response adminRegister(String incomingData) throws Exception {
		String res = null;
		JSONObject obj = new JSONObject();
		ObjectMapper mapper = new ObjectMapper();
		userar itemEntry = mapper.readValue(incomingData, userar.class);
		Session session = SessionUtil.getSession();

		System.out.println(incomingData);
		System.out.println(itemEntry.username);
		System.out.println(itemEntry.password);
		Transaction tx = session.beginTransaction();

		a1 = new Admin(itemEntry.username, itemEntry.password);
		session.save(a1);
		tx.commit();
		session.close();
		obj.put("status", "success");
		res = obj.toString();
		return Response.ok(res).build();
	}
}

class userar {
	public String username;
	public String password;
}
