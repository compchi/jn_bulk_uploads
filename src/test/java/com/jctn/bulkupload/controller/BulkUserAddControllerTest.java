/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.controller;

import com.jctn.bulkupload.model.User;
import java.util.ArrayList;
import java.util.Collection;
import junit.framework.TestCase;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author martin
 */
public class BulkUserAddControllerTest extends TestCase {
	private BulkUserAddController controller;

	public BulkUserAddControllerTest(String testName) {
		super(testName);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		controller = new BulkUserAddController("mccomputerconsulting", "ons1pAcc3s5", "mccomputerconsulting.onsip.com");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		controller = null;
	}

	public void testInitialize() throws Exception {
		System.out.println("testInitialize");
		assertNotNull(controller.getSessionId());
		assertTrue(StringUtils.isNotBlank(controller.getSessionId()));
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
		System.out.println("bulkUpload");
		Collection<User> users = new ArrayList<User>(1);
		User user = new User();
		user.setEmail("testuser1@mccomputerconsulting.onsip.com");
		user.setFirstName("Test2");
		user.setLastName("User2");
		users.add(user);

		//This is really an integration test.
		//controller.bulkUpload(users);
	}
}
