package com.webineering.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.webineering.model.Activity;
import com.webineering.model.ActivitySearch;

public class ActivitySearchClient {

	private Client client;

	public ActivitySearchClient() {
		client = ClientBuilder.newClient();
	}

	public List<Activity> search(final String param, final List<String> searchValues, final String secondParam,
			final int durationFrom, final String thirdParam, final int durationTo) {

		final URI uri = UriBuilder.fromUri("http://localhost:8080/restdemo-services/webapi/")
				.path("search/activities")
				.queryParam(param, searchValues)
				.queryParam(secondParam, durationFrom)
				.queryParam(thirdParam, durationTo)
				.build();

		final WebTarget target = client.target(uri);

		final List<Activity> response = target.request(MediaType.APPLICATION_JSON)
				.get(new GenericType<List<Activity>>() {
				});

		System.out.println(response);
		return response;
	}

	public List<Activity> search(final ActivitySearch search) {

		final URI uri = UriBuilder.fromUri("http://localhost:8080/restdemo-services/webapi/")
				.path("search/activities")
				.build();

		final WebTarget target = client.target(uri);

		final Response response = target.request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(search, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			throw new RuntimeException(response.getStatus() + ": there was an error on the server");
		}

		return response.readEntity(new GenericType<List<Activity>>() {
		});
	}
}
