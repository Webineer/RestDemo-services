package com.webineering.repository;

import java.util.List;

import com.webineering.model.Activity;
import com.webineering.model.ActivitySearch;

public interface ActivityRepository {

	List<Activity> findAllActivities();

	Activity getActivity(String activityId);

	Activity createActivity(Activity activity);

	Activity updateActivity(Activity activity);

	void deleteActivity(String activityId);

	List<Activity> findByDescription(List<String> descriptions, int durationFrom, int durationTo);

	List<Activity> findByConstraints(ActivitySearch search);

}