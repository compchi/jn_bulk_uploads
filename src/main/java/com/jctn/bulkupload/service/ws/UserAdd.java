/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAddResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class UserAdd extends AbstractWebservice<UserAddResponse> {

	public static final String PARAM_ORGANIZATION_ID = "OrganizationId";
	public static final String PARAM_USERNAME = "Username";
	public static final String PARAM_DOMAIN = "Domain";
	public static final String PARAM_NAME = "Name";
	public static final String PARAM_EMAIL = "Email";
	public static final String PARAM_AUTH_USERNAME = "AuthUsername";
	public static final String PARAM_PASSWORD = "Password";
	public static final String PARAM_PASSWORD_CONFIRM = "PasswordConfirm";

	public UserAdd() {
		super.action = "UserAdd";
	}

	@Override
	public UserAddResponse mapJson(String jsonString) {
		UserAddResponse userAddResponse = new UserAddResponse();
		try {

			JSONObject jUserAdd = parseResultAndError(userAddResponse, jsonString, "UserAdd");
			if (jUserAdd == null) {
				return userAddResponse;
			}

			JSONObject jUser = (JSONObject) jUserAdd.get("User");
			userAddResponse.setUsername((String) jUser.get("Username"));
			userAddResponse.setUserId(Long.parseLong((String) jUser.get("UserId")));
		} catch (Exception e) {
			throw new IllegalStateException("Error processing response for UserAdd:", e);
		}
		return userAddResponse;
	}
}
