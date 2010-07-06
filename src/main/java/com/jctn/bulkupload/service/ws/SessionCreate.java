/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.SessionCreateResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author martin
 */
public class SessionCreate extends AbstractWebservice<SessionCreateResponse> {

	private String url = "https://www.jnctn.com/restapi";
	private final String username;
	private final String password;

	/**
	 *
	 * @param username
	 * @param password
	 */
	public SessionCreate(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 *
	 * @return
	 */
	public SessionCreateResponse execute() {
		String params = "Username=" + username
				+ "&Output=json"
				+ "&Action=SessionCreate"
				+ "&password=" + password;

		return new SessionCreateResponse();
	}

	/**
	 * {@inheritDoc}
	 * @param jsonString
	 * @return
	 */
	@Override
	public SessionCreateResponse mapJson(String jsonString) {
		JSONParser parser = new JSONParser();
		SessionCreateResponse sessionCreateResponse = new SessionCreateResponse();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject) parser.parse(jsonString);
			JSONObject response = (JSONObject) jsonObject.get("Response");
			JSONObject context = (JSONObject) response.get("Context");
			parseError(sessionCreateResponse, jsonObject);
			//Grab session info
			JSONObject session = (JSONObject) context.get("Session");
			Boolean sessionEstablished = Boolean.parseBoolean((String) session.get("IsEstablished"));
			if (sessionEstablished) {
				String sessionId = (String) session.get("SessionId");
				JSONObject roles = (JSONObject) session.get("Roles");
				JSONObject role = (JSONObject) roles.get("Role");
				String roleName = (String) role.get("Name");
				sessionCreateResponse.setSessionId(sessionId);
				sessionCreateResponse.addRole(roleName);
			}
		} catch (Exception pe) {
			throw new IllegalStateException("Error processing response: ", pe);
		}
		return sessionCreateResponse;
	}
}
