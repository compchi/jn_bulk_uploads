/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.VoicemailboxAddResponse;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class VoicemailboxAddTest extends AbstractJunctionWSTest {

	public VoicemailboxAddTest(String testName) {
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
		String jsonString = loadTestResource("/voicemailadd_error_response.json");
		VoicemailboxAdd instance = new VoicemailboxAdd();
		VoicemailboxAddResponse expResult = new VoicemailboxAddResponse();
		Map errMap = new JSONObject();
		errMap.put("Parameter", "SessionId");
		errMap.put("Code", "Session.Authentication");
		errMap.put("Message", "An authenticated session is required for this request.");
		expResult.setErrors(errMap);

		VoicemailboxAddResponse result = instance.mapJson(jsonString);

		assertEquals(expResult.getErrors().size(), result.getErrors().size());
		assertEquals(expResult.getErrors().keySet(), result.getErrors().keySet());
	}

	public void testMapJsonWithOutError() throws URISyntaxException, IOException {
		System.out.println("testMapJsonWithOutError");

		String jsonString = loadTestResource("/voicemailadd_good_response.json");

		VoicemailboxAdd instance = new VoicemailboxAdd();

		VoicemailboxAddResponse result = instance.mapJson(jsonString);
		assertNull(result.getErrors());
		assertEquals(new Long(11452), result.getVmBoxId());
		assertEquals(new Integer(7002), result.getMailBox());
		assertEquals(new Integer(4978), result.getPassword());
	}
}
