package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/resume") 
public class FileResource {
	@POST
	@Path("/upload") 
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(@FormDataParam("upload") InputStream is,	@FormDataParam("upload") FormDataContentDisposition formData) {
 
		String files = "e:"+Sregister.s1.getRollno();
		String fileLocation=files+formData.getFileName().substring(formData.getFileName().lastIndexOf("."));
		System.out.println(formData.getType());
		System.out.println(formData.getFileName());
		System.out.println(formData.getFileName().substring(formData.getFileName().lastIndexOf(".")));

		try {
			saveFile(is, fileLocation);
			String result = "Successfully File Uploaded on the path "+fileLocation;
			return Response.status(Status.OK).entity(result).build();
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			
		}
	} 	
	private void saveFile(InputStream is, String fileLocation) throws IOException {
		
	    	OutputStream os = new FileOutputStream(new File(fileLocation));
			byte[] buffer = new byte[256];
		    int bytes = 0;
		    while ((bytes = is.read(buffer)) != -1) {
		        os.write(buffer, 0, bytes);
		    }
  }
}
 