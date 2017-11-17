package com.webineering;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.webineering.model.Activity;
import com.webineering.model.ActivitySearch;
import com.webineering.repository.ActivityRepository;
import com.webineering.repository.ActivityRepositoryStub;

@Path("search/activities")
public class ActivitySearchResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchForActivities(@QueryParam(value = "description") final List<String> descriptions,
			@QueryParam(value = "durationFrom") final int durationFrom,
			@QueryParam(value = "durationTo") final int durationTo) {
		System.out.println(descriptions + ", " + durationFrom + ", " + durationTo);

		final List<Activity> activities = activityRepository.findByDescription(descriptions, durationFrom, durationTo);

		if (activities == null || activities.size() <= 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {
		}).build();

	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchForActivities(final ActivitySearch search) {

		System.out.println(search.getDescriptions());

		final List<Activity> activities = activityRepository.findByConstraints(search);

		if (activities == null || activities.size() <= 0) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(new GenericEntity<List<Activity>>(activities) {
		}).build();
	}
}
