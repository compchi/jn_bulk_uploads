/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jctn.bulkupload.service.ws;

import com.jctn.bulkupload.model.json.OrganizationReadResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author martin
 */
public class OrganizationRead extends AbstractWebservice<OrganizationReadResponse> {

	@Override
	public OrganizationReadResponse mapJson(String jsonString) {
		JSONParser parser = new JSONParser();
		OrganizationReadResponse result = null;
		try {
			result = parseError(OrganizationReadResponse.class, jsonString, parser);
			JSONObject jsonObj = (JSONObject) parser.parse(jsonString);
			JSONObject response = (JSONObject)jsonObj.get("Response");
			JSONObject jresult = (JSONObject) response.get("Result");
			if (jresult == null) {
				return result;
			}

			JSONObject jOrgRead = (JSONObject) jresult.get("OrganizationRead");
			JSONObject jOrg = (JSONObject) jOrgRead.get("Organization");
			Long orgId = Long.parseLong((String) jOrg.get("OrganizationId"));
			result.setOrgId(orgId);

		} catch (Exception e) {
			throw new IllegalStateException("Error processing response:", e);
		}

		return result;
	}
}
