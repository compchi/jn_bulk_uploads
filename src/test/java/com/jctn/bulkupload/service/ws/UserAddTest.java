/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAddResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class UserAddTest extends AbstractJunctionWSTest {

	public UserAddTest(String testName) {
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
		String jsonString = loadTestResource("/useradd_error_response.json");
		UserAdd instance = new UserAdd();
		UserAddResponse expResult = new UserAddResponse();
		Map errMap = new JSONObject();
		errMap.put("Parameter", "SessionId");
		errMap.put("Code", "Session.Authentication");
		errMap.put("Message", "An authenticated session is required for this request.");
		expResult.setErrors(errMap);

		UserAddResponse result = instance.mapJson(jsonString);

		assertEquals(expResult.getErrors().size(), result.getErrors().size());
		assertEquals(expResult.getErrors().keySet(), result.getErrors().keySet());
	}

	public void testMapJsonWithOutError() throws URISyntaxException, IOException {
		System.out.println("testMapJsonWithOutError");

		String jsonString = loadTestResource("/useradd_good_response.json");

		UserAdd instance = new UserAdd();

		UserAddResponse result = instance.mapJson(jsonString);
		assertNull(result.getErrors());
		assertEquals(new Long(12345), result.getUserId());
		assertEquals("testuser0", result.getUsername());
	}

	public void testCreateAuthUsername() throws Exception {
		UserAdd instance = new UserAdd();
		//period and hyphen in email address
		String email = "martin.constantine-@mccc.com";
		String domain = "short.onsip.com";
		String result = instance.createAuthUsername(email, domain);
		String expected = "martin.constantine-";
		assertEquals(expected, result);

		//too long
		email = "martin.constantine@mccc.com";
		domain = "moccomputerconsulting.onsip.com";
		result = instance.createAuthUsername(email, domain);
		expected = "martin.constantine";
		assertEquals(expected, result);

		//Illegal chars in email username
		email = "martin)const*antine@mccc.com";
		domain = "moccomputerconsulting.onsip.com";
		result = instance.createAuthUsername(email, domain);
		expected = "martin_const_antine";
		assertEquals(expected, result);

		//Normal case
		email = "martin@mccc.com";
		domain = "short.onsip.com";
		result = instance.createAuthUsername(email, domain);
		expected = "short_martin";
		assertEquals(expected, result);

		//Unsalvagable
		email = "martin)constantine_of*mccomputerconsulting@mccc.com";
		domain = "moccomputerconsulting.onsip.com";
		result = instance.createAuthUsername(email, domain);
		expected = "user";
		assertEquals(expected, result);
	}
}
