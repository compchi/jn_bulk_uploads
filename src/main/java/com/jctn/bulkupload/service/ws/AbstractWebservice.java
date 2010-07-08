/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.AbstractJSONResponse;
import java.io.IOException;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author martin
 */
public abstract class AbstractWebservice<T extends AbstractJSONResponse> implements JsonMapper<T> {

	protected String action;
	protected String sessionId;
	private static final String DEFAULT_RESPONSE_FORMAT = "json";
	private static final String BASE_SERVICE_URL = "https://www.jnctn.com/restapi";
	private static final String PARAM_OUTPUT = "Output";
	private static final String PARAM_ACTION = "Action";
	private static final HttpConnector httpConnector = new HttpConnector();
	/**
	 * Name for parameter for the session id.
	 */
	public static final String PARAM_SESSION_ID = "SessionId";

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	/**
	 * Sends a RESTful request to the service with the given parameters. Work is
	 * delegated to {@link HttpConnector#sendRequest(java.lang.String, java.util.Map) HttpConnector.sendRequest}
	 * @param parameters Map of parameter entries
	 * @return Response object of type T
	 * @throws IOException
	 */
	public T sendRequest(Map parameters) throws IOException {
		parameters.put(PARAM_OUTPUT, DEFAULT_RESPONSE_FORMAT);
		parameters.put(PARAM_ACTION, this.action);
		String response = httpConnector.sendRequest(BASE_SERVICE_URL, parameters);
		return mapJson(response);
	}

	protected void parseError(T responseObj, JSONObject parsedFile) {

		JSONObject response = (JSONObject) parsedFile.get("Response");
		JSONObject context = (JSONObject) response.get("Context");
		JSONObject request = (JSONObject) context.get("Request");
		//Errors
		JSONObject errors = (JSONObject) request.get("Errors");
		if (errors != null) {
			Map errMap = (Map) errors.get("Error");
			responseObj.setErrors(errMap);
		}
	}

	protected JSONObject parseResultAndError(T responseObj, String jsonString, String serviceName) throws ParseException {
		if (responseObj == null) {
			throw new IllegalArgumentException("responseObj cannot be null");
		}

		JSONObject resultObj = null;

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
		parseError(responseObj, jsonObject);
		JSONObject response = (JSONObject) jsonObject.get("Response");
		JSONObject jresult = (JSONObject) response.get("Result");
		if (jresult != null) {
			resultObj = (JSONObject) jresult.get(serviceName);
		}

		return resultObj;
	}
}
