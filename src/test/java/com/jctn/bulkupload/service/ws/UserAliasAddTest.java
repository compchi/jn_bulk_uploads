/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAliasAddResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class UserAliasAddTest extends AbstractJunctionWSTest {

	public UserAliasAddTest(String testName) {
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
		String jsonString = loadTestResource("/useraliasadd_error_response.json");
		UserAliasAdd instance = new UserAliasAdd();
		UserAliasAddResponse expResult = new UserAliasAddResponse();
		Map errMap = new JSONObject();
		errMap.put("Parameter", "AliasUsername");
		errMap.put("Code", "UserAlias.Unavailable");
		errMap.put("Message", "This alias is already in use, is reserved, or is otherwise unavailable for use.");
		expResult.setErrors(errMap);

		UserAliasAddResponse result = instance.mapJson(jsonString);

		assertEquals(expResult.getErrors().size(), result.getErrors().size());
		assertEquals(expResult.getErrors().keySet(), result.getErrors().keySet());
	}

	public void testMapJsonWithOutError() throws URISyntaxException, IOException {
		System.out.println("testMapJsonWithOutError");

		String jsonString = loadTestResource("/useraliasadd_good_response.json");

		UserAliasAdd instance = new UserAliasAdd();

		UserAliasAddResponse result = instance.mapJson(jsonString);
		assertNull(result.getErrors());
		assertEquals("7002", result.getExtension());
	}
}
