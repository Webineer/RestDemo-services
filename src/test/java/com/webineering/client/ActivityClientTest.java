package com.webineering.client;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Ignore;
import org.junit.Test;

import com.webineering.model.Activity;
import com.webineering.model.ActivitySearch;
import com.webineering.model.ActivitySearchType;

@Ignore
public class ActivityClientTest {
	
	@Test
	public void testGetMyResource() {
		final ActivityClient client = new ActivityClient();

		final Response activity = client.getMyResource();

		System.out.println(activity);

		assertNotNull(activity);
	}

	@Test
	public void testGet() {
		final ActivityClient client = new ActivityClient();

		final Response activity = client.get("12");

		System.out.println(activity);

		assertNotNull(activity);
	}

	@Test
	public void testGetList() {
		final ActivityClient client = new ActivityClient();

		final List<Activity> activities = client.get();

		assertNotNull(activities);
	}

	@Test(expected = RuntimeException.class)
	public void testGetWithBadRequest() {
		final ActivityClient client = new ActivityClient();

		client.get("2");

	}

	@Test(expected = RuntimeException.class)
	public void testGetWithNotFound() {
		final ActivityClient client = new ActivityClient();

		client.get("7777");

	}

	@Test
	public void testCreate() {
		final ActivityClient client = new ActivityClient();

		final Activity activity = new Activity();
		activity.setDescription("Swimming");
		activity.setDuration(45);

		final Response response = client.create(activity);
		assertNotNull(response);
	}

	@Test
	public void testPut() {
		final Activity activity = new Activity();
		activity.setId("3456");
		activity.setDescription("Yoga");
		activity.setDuration(90);

		final ActivityClient client = new ActivityClient();

		final Response response = client.update(activity);
		assertNotNull(response);
	}

	@Test
	public void testDelete() {
		final ActivityClient client = new ActivityClient();

		client.deleteActivity("8765");

	}

	@Test
	public void testSearch() {
		final ActivitySearchClient client = new ActivitySearchClient();

		final String param = "description";
		final List<String> searchValues = new ArrayList<String>();
		searchValues.add("Swimming");
		searchValues.add("Running");

		final String secondParam = "durationFrom";
		final int durationFrom = 30;

		final String thirdParam = "durationTo";
		final int durationTo = 54;

		final List<Activity> activities = client.search(param, searchValues, secondParam, durationFrom, thirdParam,
				durationTo);

		System.out.println(activities);
		assertNotNull(activities);
	}

	@Test
	public void testSearchObject() {
		final ActivitySearchClient client = new ActivitySearchClient();

		final List<String> searchValues = new ArrayList<String>();
		searchValues.add("Biking");
		searchValues.add("Running");

		final ActivitySearch search = new ActivitySearch();
		search.setDescriptions(searchValues);
		search.setDurationFrom(30);
		search.setDurationTo(55);
		search.setSearchType(ActivitySearchType.SEARCH_BY_DESCRIPTION);

		final List<Activity> activities = client.search(search);

		System.out.println(activities);

		assertNotNull(activities);
	}
}
