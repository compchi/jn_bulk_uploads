/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author martin
 */
public class BulkUploaderControllerTest extends TestCase {

	public BulkUploaderControllerTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Test of validateInput method, of class BulkUploaderController.
	 */
	public void testValidateInput() {
		System.out.println("validateInput");
		List<String> errors = new ArrayList<String>();
		String adminUsername = "";
		char[] password = null;
		String adminDomain = "";
		File csvFile = null;
		BulkUploaderController instance = new BulkUploaderController();
		boolean expResult = false;
		boolean result = instance.validateInput(errors, adminUsername, password, adminDomain, csvFile);
		assertEquals(expResult, result);
		assertEquals(4, errors.size());
	}
}
