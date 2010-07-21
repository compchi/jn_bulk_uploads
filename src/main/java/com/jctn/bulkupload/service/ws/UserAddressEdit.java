/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAddressEditResponse;
import java.io.IOException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 *
 * @author martin
 */
public class UserAddressEdit extends AbstractWebservice<UserAddressEditResponse> {

	public static final String PARAM_USER_ID = "UserId";
	public static final String PARAM_USERNAME = "Username";
	/**
	 * Parameter name for VM timeout. The value is the number of seconds to wait before the VM kicks in.
	 */
	private static final String PARAM_TIMEOUT = "Timeout";
	/**
	 * Parameter for user address. Value must be &lt;authUsername&gt;@&lt;domain&gt;.
	 */
	public static final String PARAM_ADDRESS = "Address";
	/**
	 * Parameter for default address. This is the voicemail address in the form of &lt;vm_username&gt;@&lt;domain&gt;.
	 */
	public static final String PARAM_DEFAULT_ADDRESS = "DefaultAddress";

	public UserAddressEdit() {
		super.action = "UserAddressEdit";
	}

	@Override
	public UserAddressEditResponse sendRequest(Map parameters) throws IOException {
		parameters.put(PARAM_TIMEOUT, "20");
		return super.sendRequest(parameters);
	}



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
