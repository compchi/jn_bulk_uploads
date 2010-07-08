/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.SessionCreateResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class SessionCreateTest extends AbstractJunctionWSTest {

	public SessionCreateTest(String testName) {
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
	 * Test of mapJson method, of class SessionCreate.
	 */
	public void testMapJsonWithError() throws URISyntaxException, IOException {
		System.out.println("testMapJsonWithError");

		String jsonString = loadTestResource("/session_create_error_response.json");

		SessionCreate instance = new SessionCreate();
		SessionCreateResponse expResult = new SessionCreateResponse();
		Map errMap = new JSONObject();
		errMap.put("Parameter", "Password");
		errMap.put("Code", "Value.Required");
		errMap.put("Message", "This value is required.");
		expResult.setErrors(errMap);
		SessionCreateResponse result = instance.mapJson(jsonString);
		assertEquals(expResult.getErrors().size(), result.getErrors().size());
		assertEquals(expResult.getErrors().keySet(), result.getErrors().keySet());
	}

	public void testMapJsonWithOutError() throws URISyntaxException, IOException {
		System.out.println("testMapJsonWithOutError");

		String jsonString = loadTestResource("/session_create_good_response.json");

		SessionCreate instance = new SessionCreate();
		SessionCreateResponse result = instance.mapJson(jsonString);
		assertNull(result.getErrors());
		assertEquals("Account Admin", result.getRoles().get(0));
		assertEquals("7ha22nvpmgnuj302tallnbisl0", result.getSessionId());
	}
}
