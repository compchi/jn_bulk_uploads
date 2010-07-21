/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAddressEditResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class UserAddressEditTest extends AbstractJunctionWSTest {

	public UserAddressEditTest(String testName) {
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
		String jsonString = loadTestResource("/useraddressedit_error_response.json");
		UserAddressEdit instance = new UserAddressEdit();
		UserAddressEditResponse expResult = new UserAddressEditResponse();
		Map errMap = new JSONObject();
		errMap.put("Parameter", "Address");
		errMap.put("Code", "Value.Required");
		errMap.put("Message", "This value is required.");
		expResult.setErrors(errMap);

		UserAddressEditResponse result = instance.mapJson(jsonString);

		assertEquals(expResult.getErrors().size(), result.getErrors().size());
		assertEquals(expResult.getErrors().keySet(), result.getErrors().keySet());
	}

	public void testMapJsonWithOutError() throws URISyntaxException, IOException {
		System.out.println("testMapJsonWithOutError");

		String jsonString = loadTestResource("/useraddressedit_good_response.json");

		UserAddressEdit instance = new UserAddressEdit();

		UserAddressEditResponse result = instance.mapJson(jsonString);
		assertNull(result.getErrors());
	}
}
