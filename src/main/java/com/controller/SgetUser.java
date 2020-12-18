package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

@Path("/getUser")

public class SgetUser {
	public static String rootUser = null;
	public static String root = null;

	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUser(String incomingData) throws Exception {
		String res = null;
		JSONObject obj = new JSONObject();

		if (Sregister.s1 != null) {
			obj.put("status", "success");
			obj.put("username", Sregister.s1.getRollno());
			System.out.println("success");
		} else if (Aregister.a1 != null) {
			obj.put("status", "success");
			obj.put("username", Aregister.a1.getUsername());
			System.out.println("success");
		} else {
			obj.put("status", "failure");
			System.out.println("failure");

		}

		res = obj.toString();
		return Response.ok(res).build();
	}
}
