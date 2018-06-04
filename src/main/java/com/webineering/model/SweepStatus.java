package com.webineering.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SweepStatus {
	
	private String sweepName;
	private String linkUrl;
	private boolean errorStatus;
	
	//default constructor
	public SweepStatus() {		
	}
	
	public SweepStatus(String sweepName, String linkUrl, boolean errorStatus) {
		this.setSweepName(sweepName);
		this.setLinkUrl(linkUrl);
		this.setErrorStatus(errorStatus);
	}
	
	
	public String getSweepName() {
		return sweepName;
	}
	
	public void setSweepName(String sweepName) {
		this.sweepName = sweepName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}


}
