package com.webineering.services;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.webineering.model.SweepStatus;
import com.webineering.services.SweepStatusService;

import junit.framework.Assert;

public class SweepStatusServiceTest {
	SweepStatusService cut = new SweepStatusService();
	
	File testFileNot = new File("myText.txt");
	File testFileEmpty = new File("C:\\Users\\gscarfo\\Desktop\\myNoText.txt");
	File testFile = new File("C:\\Users\\gscarfo\\Desktop\\myText.txt");
	File realFile = new File("C:\\Users\\gscarfo\\Desktop\\Baltic.Close.txt");
	File realFile2 = new File("C:\\Users\\gscarfo\\Desktop\\Austria_Vienna.Close.txt");
	
	String[] directoryList = new String[] { "C:\\Users\\gscarfo\\Desktop", "C:\\Users\\gscarfo\\Desktop\\OnboardingDocs" };
	String[] directoryListEmpty = new String[] { };
	String[] directoryListNull = null;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReadTextFile() {
		SweepStatus ss = cut.readTextFile(testFile);
		assertNotNull(ss.getSweepName());
		assertTrue(ss.isErrorStatus());
	}
	
	@Ignore
	@Test(expected = FileNotFoundException.class)
	public void testReadTextFileFileNotFound() {
		SweepStatus ss = cut.readTextFile(testFileNot);
	}
	
	@Test
	public void testReadTextFileEmpty() {
		SweepStatus ss = cut.readTextFile(testFileEmpty);
		assertNotNull(ss.getSweepName());
		assertTrue(ss.isErrorStatus());
	}
	
	@Test
	public void testReadTextFileRealFileBad() {
		SweepStatus ss = cut.readTextFile(realFile);
		assertNotNull(ss.getSweepName());
		assertFalse(ss.isErrorStatus());
	}
	
	@Test
	public void testReadTextFileRealFileGood() {
		SweepStatus ss = cut.readTextFile(realFile2);
		assertNotNull(ss.getSweepName());
		assertTrue(ss.isErrorStatus());
	}
	
	@Test
	public void testGetListOfAllTxtFiles() {
		String directoryName = "C:\\Users\\gscarfo\\Desktop";
		File[] fileList = cut.getListOfAllTxtFiles(directoryName);
		System.out.println("the first value is " + fileList[0].getName());
		assertNotNull(fileList.toString());
		assertNotNull("first listing is null", fileList[0]);
		assertTrue(fileList.length > 0);
	}
	
	@Ignore
	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void testGetListOfAllTxtFilesNoFiles() {
		String directoryName = "C:\\Users\\gscarfo\\Desktop\\OnboardingDocs";
		File[] fileList = cut.getListOfAllTxtFiles(directoryName);
	}
	
	@Test
	public void testReadTextFiles() {
		ArrayList<SweepStatus> results = cut.readTextFiles(directoryList);
		assertTrue(results.size() > 0);
		assertNotNull(results.get(0));
	}
	
	@Ignore
	@Test(expected = IndexOutOfBoundsException.class)
	public void testReadTextFilesEmptyDirList() {
		ArrayList<SweepStatus> results = cut.readTextFiles(directoryListEmpty);
	}
	
	@Test(expected = NullPointerException.class)
	public void testReadTextFilesNullDirList() {
		ArrayList<SweepStatus> results = cut.readTextFiles(directoryListNull);
	}
}
