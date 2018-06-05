package com.webineering.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.WildcardFileFilter;

import com.webineering.model.SweepStatus;

public class SweepStatusService implements ISweepStatusService {
	
	//ResourceBundle rb = ResourceBundle.getBundle("restdemo");

/**
 * This method reads a sweep file and determines if there are any comparisons that have differences
 * param file - sweep text file
 * return - the sweep status object; a summary of the sweep name, sweep file url, and the status of the file
 */
	@Override
	public SweepStatus readTextFile(File file) {
		System.out.println("the name is " + file.getName());
		String fileName = this.stripExtension(file.getName());
		System.out.println("the stripped name is " +fileName);
		
		//local link
		String link = "http://localhost:8080/icedc/ppe1_cptnpa1/" + file.getName();
		//dev link
		//String link = "http://capgui1-devvm:8080/icedc/ppe1_cptnpa1/";
		
		String currentDayRecordsProd = "";
		String currentDayRecordsTest = "";
		String primaryKeysProd = "";
		String primaryKeysTest = "";
		
		//ResourceBundle rb = ResourceBundle.getBundle("restdemo");
		//System.out.println("the directory is " + rb.getString("digestDirectory"));
		
		SweepStatus theStatus = new SweepStatus(fileName, link, true);
		
        // Prepare a Scanner that will "scan" the document
        Scanner opnScanner;
		try {
			opnScanner = new Scanner(file);
			
			// Read each line in the file
	        while( opnScanner.hasNext() ) {
	            // Read each line and display its value
	        	String myString = opnScanner.nextLine();
	        	
	        	//current day records prod
	        	if (myString.contains("Total number of current day records in the production file")) {
	        		String[] tempRecordsArray = myString.split(" ");
	        		currentDayRecordsProd = tempRecordsArray[10];
	        		//System.out.println("records prod is " + tempRecordsArray[10]);
	        	}
	        	//current day records test
	        	if (myString.contains("Total number of current day records in the test file")) {
	        		String[] tempRecordsArray2 = myString.split(" ");
	        		currentDayRecordsTest = tempRecordsArray2[10];
	        		//System.out.println("records test is " + tempRecordsArray2[10]);
	        	}
	        	
	        	//primary keys prod
	        	if (myString.contains("Total number of PrimaryKeys in the production file not in the test file")) {
	        		String[] tempRecordsArray3 = myString.split(" ");
	        		primaryKeysProd = tempRecordsArray3[13];
	        		//System.out.println("primary keys prod is " + tempRecordsArray3[13]);
	        	}
	        	//primary keys test
	        	if (myString.contains("Total number of PrimaryKeys in the test file not in the production file")) {
	        		String[] tempRecordsArray4 = myString.split(" ");
	        		primaryKeysTest = tempRecordsArray4[13];
	        		//System.out.println("primary keys test is " + tempRecordsArray4[13]);
	        	}
	        	
	        	//do not match 0 test
	        	if (myString.contains("Total number of") && myString.contains("do not match")) {
	        		
	        		String[] tempMatchArray = myString.split(" ");
	        		if (!tempMatchArray[tempMatchArray.length-1].equals("0")) {
	        			System.out.println("match result: " + myString);
	        			theStatus.setErrorStatus(false);
		        		break;
	        		}
	        	} 	
	        }
	            
	        //do checks
	        if (!currentDayRecordsProd.equals(currentDayRecordsTest)) {
	        	System.out.println("current day records mismatch: " + currentDayRecordsProd + " != " + currentDayRecordsTest);
	        	theStatus.setErrorStatus(false);
	        }
	        
	        if (!primaryKeysProd.equals(primaryKeysTest)) {
	        	System.out.println("primary keys mismatch: " + primaryKeysProd + " != " + primaryKeysTest);
	        	theStatus.setErrorStatus(false);
	        }
	        
	    	// De-allocate the memory that was used by the scanner
	        opnScanner.close();
		} catch (FileNotFoundException fne) {
			// TODO Auto-generated catch block
			fne.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("the sweep file name is " + theStatus.getSweepName() + " and the link is " + theStatus.getLinkUrl());
		if (theStatus.isErrorStatus()) {
			System.out.println("sweep compare good");
		} else {
			System.out.println("sweep compare bad");
		}
		return theStatus;
		
	}
	
	public ArrayList<SweepStatus> readTextFiles() {
		ArrayList<SweepStatus> sweepStatusResults = new ArrayList<SweepStatus>();
		File[] fileList = ;
		
		
		return sweepStatusResults;
	}
	
	/**
	 * This method used to strip the file extension off of the file name
	 * @param str
	 * @return
	 */
	public String stripExtension(String str) {

	   if (str == null) return null;
	   
	   // Get position of last '.'.
	   int pos = str.lastIndexOf(".");

	   // If there wasn't any '.' just return the string as is.
	   if (pos == -1) return str;

	   // Otherwise return the string, up to the dot.
       return str.substring(0, pos);
   }
	
	public File[] getListOfAllTxtFiles(String directoryName) {
	    //File directory = new File(directoryName);
	    //return FileUtils.listFiles(directory, new WildcardFileFilter("*.txt"), null);
		
		File dir = new File(directoryName);
		File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));
		return files;
	}
	
}
