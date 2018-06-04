package com.webineering.services;

import java.io.File;
import java.io.FileNotFoundException;

import com.webineering.model.SweepStatus;

public interface ISweepStatusService {
	
	public SweepStatus readTextFile(File file);

}
