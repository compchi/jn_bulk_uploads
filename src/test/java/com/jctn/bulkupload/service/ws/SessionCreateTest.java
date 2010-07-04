/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.SessionCreateResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;
import junit.framework.TestCase;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class SessionCreateTest extends TestCase {

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
     * Test of execute method, of class SessionCreate.
     */
    public void testExecute() {
//        System.out.println("execute");
//        SessionCreate instance = null;
//        SessionCreateResponse expResult = null;
//        SessionCreateResponse result = instance.execute();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of mapJson method, of class SessionCreate.
     */
    public void testMapJsonWithError() throws URISyntaxException, IOException {
        System.out.println("testMapJsonWithError");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                SessionCreateTest.class.getResourceAsStream("/session_create_error_response.json")));
        String jsonString = "";

        while (reader.ready()) {
            jsonString += reader.readLine();
        }
        reader.close();

        SessionCreate instance = new SessionCreate(null, null);
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

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                SessionCreateTest.class.getResourceAsStream("/session_create_good_response.json")));
        String jsonString = "";

        while (reader.ready()) {
            jsonString += reader.readLine();
        }

        reader.close();

        SessionCreate instance = new SessionCreate(null, null);
        SessionCreateResponse result = instance.mapJson(jsonString);
        assertNull(result.getErrors());
        assertEquals("Account Admin", result.getRoles().get(0));
        assertEquals("7ha22nvpmgnuj302tallnbisl0", result.getSessionId());
    }
}
