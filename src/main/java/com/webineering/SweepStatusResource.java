package com.webineering;

import java.io.File;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.webineering.model.SweepStatus;
import com.webineering.services.SweepStatusService;

@Path("/status")
public class SweepStatusResource {
	
	private SweepStatusService service = new SweepStatusService();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSweepStatus(@PathParam("fileName") File fileName) {
		
		SweepStatus sweepStatus = new SweepStatus();
		
		if (fileName.isFile() && fileName.canRead()) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		sweepStatus = service.readTextFile(fileName);
		
		return Response.ok().entity(sweepStatus).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("all")
	public Response getAllSweepStatus() {
		
		ArrayList<SweepStatus> sweepStatusList = new ArrayList<SweepStatus>();
		//String[] directoryList = new String[] { 
		//		"/home/prod/apache-tomcat-6.0.29/webapps/icedc/ppe1_cptnpa1", 
		//		"/home/prod/apache-tomcat-6.0.29/webapps/icedc/ppe1_cptnpa1/Dataline", 
		//		"/home/prod/apache-tomcat-6.0.29/webapps/icedc/ppe1_cptnpa1/sigma" 
		//};
		
		String[] directoryList = new String[] { "C:\\Users\\gscarfo\\Desktop", "C:\\Users\\gscarfo\\Desktop\\OnboardingDocs" };
		
		if (directoryList.length == 0) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
		sweepStatusList = service.readTextFiles(directoryList);
		
		return Response.ok().entity(sweepStatusList).build();
	}

}
