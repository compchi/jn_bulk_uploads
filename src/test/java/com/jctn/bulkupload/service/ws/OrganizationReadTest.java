/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.OrganizationReadResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class OrganizationReadTest extends AbstractJunctionWSTest {

	public OrganizationReadTest(String testName) {
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
	 * Test of mapJson method, of class OrganizationRead.
	 */
	public void testMapJsonWithError() throws IOException {
		System.out.println("testMapJsonWithError");
		String jsonString = loadTestResource("/org_read_error_response.json");
		OrganizationRead instance = new OrganizationRead();
		OrganizationReadResponse expResult = new OrganizationReadResponse();
		Map errMap = new JSONObject();
		errMap.put("Parameter", "Domain");
		errMap.put("Code", "Domain.Unrecognized");
		errMap.put("Message", "This domain is not recognized by the system.");
		expResult.setErrors(errMap);

		OrganizationReadResponse result = instance.mapJson(jsonString);

		assertEquals(expResult.getErrors().size(), result.getErrors().size());
		assertEquals(expResult.getErrors().keySet(), result.getErrors().keySet());
	}

	public void testMapJsonWithOutError() throws URISyntaxException, IOException {
		System.out.println("testMapJsonWithOutError");

		String jsonString = loadTestResource("/org_read_good_response.json");

		OrganizationRead instance = new OrganizationRead();

		OrganizationReadResponse result = instance.mapJson(jsonString);
		assertNull(result.getErrors());
		assertEquals(new Long(55555), result.getOrgId());
	}
}
