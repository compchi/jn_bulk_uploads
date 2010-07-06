/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.UserAliasAddResponse;
import org.json.simple.JSONObject;

/**
 * Webservice programming interface for adding an extension to to a user's address.
 *
 * @author martin
 */
public class UserAliasAdd extends AbstractWebservice<UserAliasAddResponse> {

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
