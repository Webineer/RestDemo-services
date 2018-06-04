package com.webineering.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.webineering.model.Activity;

public class ActivityClient {

	private Client client;

	public ActivityClient() {
		client = ClientBuilder.newClient();
	}

	public Response getMyResource() {
		final WebTarget target = client.target("http://localhost:8080/restdemo-services");
		final Response response = target.path("webapi").path("myresource").request(MediaType.TEXT_PLAIN).get(Response.class);
		return response;
	}
	public Response get(final String id) {

		final WebTarget target = client.target("http://localhost:8080/restdemo-services/webapi/");

		final Response response = target.path("activities/" + id).request(MediaType.APPLICATION_JSON)
				.get(Response.class);
		// final String response = target.path("activities/" +
		// id).request(MediaType.APPLICATION_JSON).get(String.class);

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server");
		}

		// return response.readEntity(Activity.class);
		return response;
	}

	public List<Activity> get() {

		final WebTarget target = client.target("http://localhost:8080/restdemo-services/webapi/");
		final List<Activity> response = target.path("activities").request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>() {
				});
		return response;

	}

	public Response create(final Activity activity) {
		final WebTarget target = client.target("http://localhost:8080/restdemo-services/webapi/");
		final Response response = target.path("activities/activity").request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(activity, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server");
		}

		return response;
	}

	public Response update(final Activity activity) {
		final WebTarget target = client.target("http://localhost:8080/restdemo-services/webapi/");
		final Response response = target.path("activities/" + activity.getId()).request(MediaType.APPLICATION_JSON)
				.put(Entity.entity(activity, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server");
		}

		return response;
	}

	public void deleteActivity(final String activityId) {
		final WebTarget target = client.target("http://localhost:8080/restdemo-services/webapi/");

		final Response response = target.path("activities/" + activityId).request(MediaType.APPLICATION_JSON).delete();

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server");
		}

	}

}
