package com.webineering.repository;

import java.util.ArrayList;
import java.util.List;

import com.webineering.model.Activity;
import com.webineering.model.ActivitySearch;
import com.webineering.model.User;

public class ActivityRepositoryStub implements ActivityRepository {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.webineering.repository.ActivityRepository#findAllActivities()
	 */
	@Override
	public List<Activity> findAllActivities() {
		final List<Activity> activities = new ArrayList<Activity>();

		final Activity activity1 = new Activity();
		activity1.setDescription("Swimming");
		activity1.setDuration(55);

		activities.add(activity1);

		final Activity activity2 = new Activity();
		activity2.setDescription("Cycling");
		activity2.setDuration(120);

		activities.add(activity2);

		return activities;
	}

	@Override
	public Activity getActivity(final String activityId) {

		if (activityId.equals("7777")) {
			return null;
		}

		final Activity activity1 = new Activity();
		activity1.setId("12");
		activity1.setDescription("Swimming");
		activity1.setDuration(55);

		final User user = new User();
		user.setId("34");
		user.setName("Greg");

		activity1.setUser(user);

		return activity1;
	}

	@Override
	public Activity createActivity(final Activity activity) {
		// enter activity into db
		return activity;
	}

	@Override
	public Activity updateActivity(final Activity activity) {
		// search the db for the activity
		// if not found, create
		// if found, update
		// update activity into db
		return activity;
	}

	@Override
	public void deleteActivity(final String activityId) {
		// delete the activity
	}

	@Override
	public List<Activity> findByDescription(final List<String> descriptions, final int durationFrom,
			final int durationTo) {
		// do a search
		final List<Activity> activities = new ArrayList<Activity>();

		final Activity activity = new Activity();
		activity.setId("234");
		activity.setDescription("Swimming");
		activity.setDuration(55);

		activities.add(activity);

		return activities;
	}

	@Override
	public List<Activity> findByConstraints(final ActivitySearch search) {

		System.out.println(search.getDurationTo());
		System.out.println(search.getSearchType());

		// do a search
		final List<Activity> activities = new ArrayList<Activity>();

		final Activity activity = new Activity();
		activity.setId("234");
		activity.setDescription("Swimming");
		activity.setDuration(55);

		activities.add(activity);

		return activities;
	}
}
