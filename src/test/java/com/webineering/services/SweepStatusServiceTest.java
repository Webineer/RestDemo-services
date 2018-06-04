package com.webineering.services;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.webineering.model.SweepStatus;
import com.webineering.services.SweepStatusService;

public class SweepStatusServiceTest {
	SweepStatusService cut = new SweepStatusService();
	File testFile = new File("/Users/greg/myText.txt");
	File testFileNot = new File("Users/greg/myText.txt");
	File testFileEmpty = new File("/Users/greg/myNoText.txt");

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
	
	@Test(expected = FileNotFoundException.class)
	public void testReadTextFileFileNotFound() {
		SweepStatus ss = cut.readTextFile(testFileNot);
	}
	
	@Test
	public void testReadTextFileEmpty() {
		SweepStatus ss = cut.readTextFile(testFileEmpty);
		assertNotNull(ss.getSweepName());
		assertFalse(ss.isErrorStatus());
	}
}
