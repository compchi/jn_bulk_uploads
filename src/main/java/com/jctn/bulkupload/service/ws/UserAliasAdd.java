/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAliasAddResponse;
import java.io.IOException;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * Webservice programming interface for adding an extension to to a user's address.
 *
 * @author martin
 */
public class UserAliasAdd extends AbstractWebservice<UserAliasAddResponse> {

	/**
	 * Parameter name for alias username. The value is usually the voicemail extension.
	 */
	public static final String PARAM_ALIAS_USERNAME = "AliasUsername";
	/**
	 * Parameter name for organization ID.
	 */
	public static final String PARAM_ORGANIZATION_ID = "OrganizationId";
	private static final String PARAM_VISIBILITY = "Visibility";
	private static final String DEFAULT_VISIBILITY = "PUBLIC";
	/**
	 * Parameter name for extension username.
	 */
	public static final String PARAM_ADDRESS_USERNAME = "AddressUsername";

	public UserAliasAdd() {
		super.action = "UserAliasAdd";
	}

	@Override
	public UserAliasAddResponse sendRequest(Map parameters) throws IOException {
		parameters.put(PARAM_VISIBILITY, DEFAULT_VISIBILITY);
		return super.sendRequest(parameters);
	}

	@Override
	public UserAliasAddResponse mapJson(String jsonString) {
		UserAliasAddResponse aliasAddResponse = new UserAliasAddResponse();
		try {
			JSONObject serviceObj = parseResultAndError(aliasAddResponse, jsonString, UserAliasAdd.class.getSimpleName());
			if (serviceObj == null) {
				return aliasAddResponse;
			}

			JSONObject jUserAlias = (JSONObject) serviceObj.get("UserAlias");
			aliasAddResponse.setExtension((String) jUserAlias.get("AliasUsername"));
		} catch (Exception e) {
			throw new IllegalStateException("Error processing UserAliasAdd response:", e);
		}

		return aliasAddResponse;
	}
}
