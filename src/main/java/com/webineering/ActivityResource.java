package com.webineering;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.webineering.model.Activity;
import com.webineering.model.User;
import com.webineering.repository.ActivityRepository;
import com.webineering.repository.ActivityRepositoryStub;

@Path("activities")
public class ActivityResource {

	private ActivityRepository activityRepository = new ActivityRepositoryStub();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Activity> getAllActivities() {
		return activityRepository.findAllActivities();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{activityId}")
	public Response getActivity(@PathParam("activityId") final String activityId) {

		if (activityId == null || activityId.length() < 2) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		final Activity activity = activityRepository.getActivity(activityId);

		if (activity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(activity).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{activityId}/user")
	public User getActivityUser(@PathParam("activityId") final String activityId) {
		return activityRepository.getActivity(activityId).getUser();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("activity")
	public Response create(final Activity activity) {

		System.out.println(activity.getDescription());
		System.out.println(activity.getDuration());

		// return activityRepository.createActivity(activity);

		final Activity createdActivity = activityRepository.createActivity(activity);

		if (createdActivity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(createdActivity).build();
	}

	/*
	 * @POST
	 * 
	 * @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 * 
	 * @Produces(MediaType.APPLICATION_XML)
	 * 
	 * @Path("activity")
	 * public Activity createActivityParams(final MultivaluedMap<String, String> formParams) {
	 * 
	 * System.out.println(formParams.getFirst("description"));
	 * System.out.println(formParams.getFirst("duration"));
	 * 
	 * final Activity activity = new Activity();
	 * activity.setDescription(formParams.getFirst("description"));
	 * activity.setDuration(Integer.parseInt(formParams.getFirst("duration")));
	 * 
	 * return activityRepository.createActivity(activity);
	 * }
	 */

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{activityId}")
	public Response update(final Activity activity) {

		System.out.println(activity.getId());

		final Activity updatedActivity = activityRepository.updateActivity(activity);

		if (updatedActivity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}

		return Response.ok().entity(updatedActivity).build();

	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{activityId}")
	public Response delete(@PathParam("activityId") final String activityId) {
		System.out.println(activityId);

		activityRepository.deleteActivity(activityId);

		return Response.ok().build();

	}

}
