/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAddressEditResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class UserAddressEdit extends AbstractWebservice<UserAddressEditResponse> {

	@Override
	public UserAddressEditResponse mapJson(String jsonString) {
		UserAddressEditResponse userAddressEditResponse = new UserAddressEditResponse();
		try {
			JSONObject serviceObj = parseResultAndError(userAddressEditResponse, jsonString, "UserAddressEdit");
			if (serviceObj == null) {
				return userAddressEditResponse;
			}

		} catch (Exception e) {
			throw new IllegalStateException("Error processing UserAddressEdit response:", e);
		}
		return userAddressEditResponse;
	}
}
