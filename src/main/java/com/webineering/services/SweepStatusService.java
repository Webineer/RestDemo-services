package com.webineering.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.webineering.model.SweepStatus;

public class SweepStatusService implements ISweepStatusService {

	@Override
	public SweepStatus readTextFile(File file) {
		
		SweepStatus theStatus = new SweepStatus();
		
        // Prepare a Scanner that will "scan" the document
        Scanner opnScanner;
		try {
			opnScanner = new Scanner(file);
			
			// Read each line in the file
	        while( opnScanner.hasNext() ) {
	            // Read each line and display its value
	        	String myString = opnScanner.nextLine();
	        	
	        	System.out.println("the content is " + myString);
	        	
	        	if(myString.equals("This is a test")) {
	        		theStatus.setSweepName("testSweep");
		        	theStatus.setErrorStatus(true);
	        	} else {
	        		theStatus.setSweepName("testSweep");
	        		theStatus.setErrorStatus(false);
	        	}
	        	
	        	
	        }
	            
	    	// De-allocate the memory that was used by the scanner
	        opnScanner.close();
		} catch (FileNotFoundException fne) {
			// TODO Auto-generated catch block
			fne.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return theStatus;
	}
	
	
}
