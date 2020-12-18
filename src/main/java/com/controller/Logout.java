package com.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logout")
public class Logout {

	@GET
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(String incomingData) throws Exception {
		String res = null;
		Aregister.a1 = null;
		Sregister.s1 = null;
		System.out.println("success");
		return Response.ok(res).build();
	}
}
