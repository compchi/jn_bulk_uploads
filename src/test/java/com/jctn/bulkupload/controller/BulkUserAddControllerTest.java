/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author martin
 */
public class BulkUserAddControllerTest extends TestCase {

	public BulkUserAddControllerTest(String testName) {
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

	public void testInitialize() throws Exception {
		System.out.println("testInitialize");
		BulkUserAddController instance = new BulkUserAddController("mccomputerconsulting", "ons1pAcc3s5", null);
		assertNotNull(instance.getSessionId());
		assertTrue(StringUtils.isNotBlank(instance.getSessionId()));
	}

	/**
	 * Test of parseCsv method, of class BulkUserAddController.
	 */
	public void testParseCsv() {
//		System.out.println("parseCsv");
//		File csvFile = null;
//		BulkUserAddController instance = null;
//		Collection expResult = null;
//		Collection result = instance.parseCsv(csvFile);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
	}

	/**
	 * Test of bulkUpload method, of class BulkUserAddController.
	 */
	public void testBulkUpload() {
//		System.out.println("bulkUpload");
//		Collection<User> users = null;
//		BulkUserAddController instance = null;
//		instance.bulkUpload(users);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
	}
}
