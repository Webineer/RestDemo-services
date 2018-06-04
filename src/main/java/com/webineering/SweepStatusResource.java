package com.webineering;

import java.io.File;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.webineering.model.SweepStatus;
import com.webineering.services.SweepStatusService;

@Path("status")
public class SweepStatusResource {
	
	private SweepStatusService service = new SweepStatusService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSweepStatus(@PathParam("fileName") File fileName) {
		
		SweepStatus sweepStatus = new SweepStatus();
		
		if (fileName.isFile() && fileName.canRead()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		return Response.ok().entity(sweepStatus).build();
	}

}
