package com.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONObject;

@Path("/email")
public class SendMail {

	private static final Logger logger = Logger.getLogger(Sregister.class);

	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON })
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(String incomingData) throws Exception {

		JSONObject obj = new JSONObject();

		System.out.println(incomingData);
		ObjectMapper mapper = new ObjectMapper();
		mail m = mapper.readValue(incomingData, mail.class);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				//put the original username and password
				return new PasswordAuthentication("", "");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(""));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(m.to));
			message.setSubject(m.subject);
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);
			obj.put("status", "success");
			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		logger.debug("mail sent");
		return Response.ok(obj.toString()).build();

	}
}

class mail {
	public String to;
	public String subject;
}
