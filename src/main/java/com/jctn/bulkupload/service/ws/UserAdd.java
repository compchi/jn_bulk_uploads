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
