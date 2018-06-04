package com.webineering.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SweepStatus {
	
	private String sweepName;
	private boolean errorStatus;
	
	//default constructor
	public SweepStatus() {
		
	}
	
	public SweepStatus(String sweepName, boolean errorStatus) {
		this.setSweepName(sweepName);
		this.setErrorStatus(errorStatus);
	}
	
	
	public String getSweepName() {
		return sweepName;
	}
	
	public void setSweepName(String sweepName) {
		this.sweepName = sweepName;
	}

	public boolean isErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(boolean errorStatus) {
		this.errorStatus = errorStatus;
	}


}
