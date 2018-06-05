package com.webineering.resources;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SweepStatusResourceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Ignore
	@Test
	public void testSweepStatusResource() {
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target("http://localhost:8080/restdemo-services/webapi/status");
		
		Response response = webTarget.request().get();
		
		if (response.getStatus() != 200) {
			System.out.println("Error invoking REST services - " + response.getStatusInfo().getReasonPhrase());
			return;
		}
		
		System.out.println(response.readEntity(String.class));
	}

}
