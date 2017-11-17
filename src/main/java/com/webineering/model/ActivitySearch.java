package com.webineering.model;

import java.util.List;

public class ActivitySearch {

	private int durationFrom;
	private int durationTo;
	private List<String> descriptions;
	private ActivitySearchType searchType;

	public int getDurationFrom() {
		return durationFrom;
	}

	public void setDurationFrom(final int durationFrom) {
		this.durationFrom = durationFrom;
	}

	public int getDurationTo() {
		return durationTo;
	}

	public void setDurationTo(final int durationTo) {
		this.durationTo = durationTo;
	}

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(final List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public ActivitySearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(final ActivitySearchType searchType) {
		this.searchType = searchType;
	}

}
